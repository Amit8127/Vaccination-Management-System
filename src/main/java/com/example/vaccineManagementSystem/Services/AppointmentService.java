package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AppointmentRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.CancelAppointmentRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.ChangeAppointmentDateRequestDtos;
import com.example.vaccineManagementSystem.Dtos.ResponcseDtos.DoctorDtoForCentre;
import com.example.vaccineManagementSystem.Enums.AppointmentStatus;
import com.example.vaccineManagementSystem.Enums.Gender;
import com.example.vaccineManagementSystem.Exceptions.*;
import com.example.vaccineManagementSystem.Models.Appointment;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import com.example.vaccineManagementSystem.Repositories.AppointmentRepository;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import com.example.vaccineManagementSystem.Repositories.VaccinationCentreRepository;
import com.example.vaccineManagementSystem.Transformers.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaccinationCentreRepository vaccinationCentreRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public String bookingAppointment(AppointmentRequestDto appointmentRequestDto) throws DoctorNotFound, UserNotFound, PendingAppointment{
        Integer doctorId = appointmentRequestDto.getDocId();
        Integer userId = appointmentRequestDto.getUserId();
        Date date = appointmentRequestDto.getAppointmentDate();
        Time time = appointmentRequestDto.getAppointmentTime();

        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        Optional<User> userOpt = userRepository.findById(userId);
        if(doctorOpt.isEmpty()) {
            throw new DoctorNotFound();
        }
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }

        Doctor doctor = doctorOpt.get();
        User user = userOpt.get();
        if(doctor.getVaccinationCentre() == null) {
            throw new DoctorIsNotAssociateAnyCenter();
        }
        Appointment appointment1 = null;
        if(user.getAppointmentList().size() > 0) {
            appointment1 = user.getAppointmentList().get(user.getAppointmentList().size() - 1);
        }
        if(appointment1 != null && appointment1.getAppointmentStatus().equals(AppointmentStatus.PENDING)) {
            throw new PendingAppointment(appointment1.getAppointmentId());
        }
        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);

        return "Your Appointment booked successfully";
    }

    public String changeDateByBookingId(ChangeAppointmentDateRequestDtos changeAppointmentDateRequestDtos) throws BookingAppointmentIsNotPresent, UserNotFound,YouCanNotChangeDate, UserDoNotHaveAppointmentId{
        Integer appointmentId = changeAppointmentDateRequestDtos.getAppointmentId();
        Integer userId = changeAppointmentDateRequestDtos.getUserId();
        Date date = changeAppointmentDateRequestDtos.getDate();
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if(appointmentOpt.isEmpty()) {
            throw new BookingAppointmentIsNotPresent();
        }
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }
        User user = userOpt.get();
        Appointment appointment = appointmentOpt.get();
        if(!appointment.getUser().equals(user)) {
            throw new UserDoNotHaveAppointmentId();
        }
        if(appointment.getAppointmentStatus().equals(AppointmentStatus.COMPLETED)) {
            throw new YouCanNotChangeDate();
        }
        appointment.setDate(date);
        appointmentRepository.save(appointment);
        return "Your new appointment is "+date;
    }

    public String deleteAppointmentById(CancelAppointmentRequestDto cancelAppointmentRequestDto) throws BookingAppointmentIsNotPresent, UserDoNotHaveAppointmentId, AppointmentCanNotDelete{
        Integer appointmentId = cancelAppointmentRequestDto.getAppointmentId();
        Integer userId = cancelAppointmentRequestDto.getUserId();
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if(appointmentOpt.isEmpty()) {
            throw new BookingAppointmentIsNotPresent();
        }
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }
        if(!appointmentOpt.get().getUser().equals(userOpt.get())) {
            throw new UserDoNotHaveAppointmentId();
        }
        Appointment appointment = appointmentOpt.get();
        if(appointment.getAppointmentStatus().equals(AppointmentStatus.COMPLETED)) {
            throw new AppointmentCanNotDelete();
        }
        Doctor doctor = appointment.getDoctor();
        User user = appointment.getUser();
        doctor.getAppointmentList().remove(appointment);
        user.getAppointmentList().remove(appointment);
        appointmentRepository.delete(appointment);

        return "Your appointmentId: "+appointmentId+" has been Deleted successfully";
    }

    public List<DoctorDtoForCentre> getAllDoctorsByCenterId(Integer centerId) throws VaccinationCentreNotFound{
        Optional<VaccinationCentre> centerOpt = vaccinationCentreRepository.findById(centerId);
        if(centerOpt.isEmpty()) {
            throw new VaccinationCentreNotFound();
        }
        List<Doctor> doctors = centerOpt.get().getDoctorList();
        List<DoctorDtoForCentre> doctorList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorList.add(DoctorTransformer.doctorToDoctorDtoForCentre(doctor));
        }
        return doctorList;
    }

    public List<DoctorDtoForCentre> getAllMaleDoctorsByCenterId(Integer centerId) throws VaccinationCentreNotFound{
        Optional<VaccinationCentre> centerOpt = vaccinationCentreRepository.findById(centerId);
        if(centerOpt.isEmpty()) {
            throw new VaccinationCentreNotFound();
        }
        List<Doctor> doctors = centerOpt.get().getDoctorList();
        List<DoctorDtoForCentre> doctorList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if(doctor.getGender().equals(Gender.MALE)) {
                doctorList.add(DoctorTransformer.doctorToDoctorDtoForCentre(doctor));
            }
        }
        return doctorList;
    }

    public List<DoctorDtoForCentre> getAllFemaleDoctorsByCenterId(Integer centerId) throws VaccinationCentreNotFound{
        Optional<VaccinationCentre> centerOpt = vaccinationCentreRepository.findById(centerId);
        if(centerOpt.isEmpty()) {
            throw new VaccinationCentreNotFound();
        }
        List<Doctor> doctors = centerOpt.get().getDoctorList();
        List<DoctorDtoForCentre> doctorList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if(doctor.getGender().equals(Gender.FEMALE)) {
                doctorList.add(DoctorTransformer.doctorToDoctorDtoForCentre(doctor));
            }
        }
        return doctorList;
    }

    public List<DoctorDtoForCentre> getAllDoctorsBasedOnAgeAndGenderByCenterId(Integer centerId, Integer greaterThenAge, Gender gender) throws VaccinationCentreNotFound{
        Optional<VaccinationCentre> centerOpt = vaccinationCentreRepository.findById(centerId);
        if(centerOpt.isEmpty()) {
            throw new VaccinationCentreNotFound();
        }
        List<Doctor> doctors = centerOpt.get().getDoctorList();
        List<DoctorDtoForCentre> doctorList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if(doctor.getAge() > greaterThenAge && doctor.getGender().equals(gender)) {
                doctorList.add(DoctorTransformer.doctorToDoctorDtoForCentre(doctor));
            }
        }
        return doctorList;
    }
}
