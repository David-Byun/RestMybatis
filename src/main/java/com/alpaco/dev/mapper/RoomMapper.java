package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.room.RoomDetailResponseDto;
import com.alpaco.dev.dto.room.RoomListResponseDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    Page<List<RoomListResponseDto>> findAllDesc(int pageNo, String orderKind);

    String findById(Long roomId);
    RoomDetailResponseDto findByRoomId(Long roomId);
}