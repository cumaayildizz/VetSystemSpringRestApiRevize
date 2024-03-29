package com.cuma.yildiz.VetSystem.api;

import com.cuma.yildiz.VetSystem.business.abstracts.IDoctorService;
import com.cuma.yildiz.VetSystem.core.config.modelMapper.IModelMapperService;
import com.cuma.yildiz.VetSystem.core.result.Result;
import com.cuma.yildiz.VetSystem.core.result.ResultData;
import com.cuma.yildiz.VetSystem.core.util.ResultHelper;
import com.cuma.yildiz.VetSystem.dto.request.doctor.DoctorSaveRequest;
import com.cuma.yildiz.VetSystem.dto.request.doctor.DoctorUpdateRequest;
import com.cuma.yildiz.VetSystem.dto.response.CursorResponse;
import com.cuma.yildiz.VetSystem.dto.response.entitiesResponse.DoctorResponse;
import com.cuma.yildiz.VetSystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vetSystem/doctors")
public class DoctorController {
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapperService) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapperService;
    }

    // Yeni bir doktor kaydı oluşturma
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        // İstekten gelen verileri Doctor sınıfına map et
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);

        // Oluşturulan doktoru kaydet
        this.doctorService.saveDoctor(saveDoctor);

        // Oluşturulan doktoru response sınıfına map et
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class);
        return ResultHelper.created(doctorResponse);
    }

    // Doktor bilgilerini güncelleme
    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        // İstekten gelen verileri Doctor sınıfına map et
        Doctor updateDoctor = modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);

        // Güncellenen doktoru kaydet
        doctorService.updateDoctor(updateDoctor);

        // Güncellenen doktoru response sınıfına map et
        DoctorResponse doctorResponse = modelMapper.forResponse().map(updateDoctor, DoctorResponse.class);
        return ResultHelper.updated(doctorResponse);
    }

    // Tüm doktorları listeleme
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<DoctorResponse>> getDoctors() {
        // Tüm doktorları getir
        List<Doctor> doctors = doctorService.getAllDoctors();

        // Doktor listesini response sınıfına map et
        List<DoctorResponse> doctorResponse = doctors.stream()
                .map(doctor -> modelMapper.forResponse().map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(doctorResponse);
    }

    // Sayfalı doktor listesi
    @GetMapping("/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize) {
        // Sayfalı doktor listesini getir
        Page<Doctor> doctorPage = this.doctorService.cursor(page, pageSize);

        // Sayfalı doktor listesini response sınıfına map et
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.forResponse()
                        .map(doctor, DoctorResponse.class));

        return ResultHelper.cursorMethod(doctorResponsePage);
    }

    // Belirli bir doktorun bilgilerini getirme
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") long id) {
        // Belirli bir doktorun bilgilerini getir
        Doctor doctor = this.doctorService.getDoctorById(id);

        // Doktor bilgilerini response sınıfına map et
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    // Belirli bir doktoru silme
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        // Belirli bir doktoru sil
        this.doctorService.deleteDoctor(id);
        return ResultHelper.deleted();
    }
}


   /*

    Doktorun müsait günlerini ekleme:

@PostMapping("/{doctorId}/available-dates")
@ResponseStatus(HttpStatus.CREATED)
public Result addAvailableDate(
        @PathVariable("doctorId") long doctorId,
        @RequestBody @Valid AvailableDateRequest availableDateRequest) {
    Doctor doctor = doctorService.get(doctorId);
    AvailableDate availableDate = modelMapper.forRequest().map(availableDateRequest, AvailableDate.class);
    doctor.addAvailableDate(availableDate);
    doctorService.update(doctor);
    return ResultHelper.created();
}
Doktorun müsait günlerini güncelleme:

@PutMapping("/{doctorId}/available-dates/{dateId}")
public Result updateAvailableDate(
        @PathVariable("doctorId") long doctorId,
        @PathVariable("dateId") long dateId,
        @RequestBody @Valid AvailableDateRequest availableDateRequest) {
    Doctor doctor = doctorService.get(doctorId);
    AvailableDate existingDate = doctor.getAvailableDates()
            .stream()
            .filter(date -> date.getId() == dateId)
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Müsait gün bulunamadı."));
    modelMapper.forRequest().mapOnto(availableDateRequest, existingDate);
    doctorService.update(doctor);
    return ResultHelper.success();
}
Doktorun müsait günlerini görüntüleme:

@GetMapping("/{doctorId}/available-dates")
public ResultData<List<AvailableDateResponse>> getAvailableDates(@PathVariable("doctorId") long doctorId) {
    Doctor doctor = doctorService.get(doctorId);
    List<AvailableDateResponse> availableDateResponses =
            modelMapper.forResponse().mapList(doctor.getAvailableDates(), AvailableDateResponse.class);
    return ResultHelper.success(availableDateResponses);
}
Doktorun çalışma saatlerini ve müsait günlerini görüntüleme:

@GetMapping("/{doctorId}/working-hours")
public ResultData<DoctorWorkingHoursResponse> getDoctorWorkingHours(@PathVariable("doctorId") long doctorId) {
    Doctor doctor = doctorService.get(doctorId);
    DoctorWorkingHoursResponse response = modelMapper.forResponse().map(doctor, DoctorWorkingHoursResponse.class);
    return ResultHelper.success(response);
}

    */