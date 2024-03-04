package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @Operation(summary = "일기 작성", description = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @Parameter(name = "date", description = "작성 날짜", example = "2024-03-01")
    @Parameter(name = "text", description = "작성할 일기 내용", example = "TEXT")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "일기 확인", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @Parameter(name = "date", description = "확인할 날짜", example = "2024-03-01")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "기간 내 모든 일기 확인", description = "선택한 기간 중의 모든 일기 데이터를 가져옵니다.")
    @Parameter(name = "startDate", description = "조회할 기간의 첫번째 날", example = "2024-03-01")
    @Parameter(name = "endDate", description = "조회할 기간의 마지막 날", example = "2024-03-01")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "일기 수정", description = "선택한 날짜의 첫 번째 일기 내용을 수정합니다.")
    @Parameter(name = "date", description = "수정할 날짜", example = "2024-03-01")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "일기 삭제", description = "선택한 날짜의 모든 일기를 삭제합니다.")
    @Parameter(name = "date", description = "삭제할 날짜", example = "2024-03-01")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
