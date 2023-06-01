package com.alpaco.dev.controller;

import com.alpaco.dev.dto.room.RoomDetailResponseDto;
import com.alpaco.dev.dto.room.RoomListResponseDto;
import com.alpaco.dev.exception.BaseResponse;
import com.alpaco.dev.service.RoomService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public BaseResponse<PageInfo<List<RoomListResponseDto>>> get(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                                                 @RequestParam(required = false, defaultValue = "ID_DESC") String sort) {

        String orderKind = sort;

        PageInfo<List<RoomListResponseDto>> listPageInfo = new PageInfo<>(roomService.findAllDesc(pageNo, orderKind));
        return new BaseResponse<>(listPageInfo);
    }

    @GetMapping("/rooms/{roomId}")
    public BaseResponse<RoomDetailResponseDto> getRoomDetail(@PathVariable("roomId") Long roomId) {

        RoomDetailResponseDto roomDetailResponseDto = roomService.getRoomDetail(roomId, null);

        return new BaseResponse<>(roomDetailResponseDto);

    }

}
