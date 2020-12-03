package com.xf.dao;

import com.xf.pojo.Order;
import com.xf.pojo.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface RoomMapper {

    List<Room> SelectAllRoom(Map<String,Object> map);

    Room ID_Select(int RoomId);

    int addRoom(Room room);

    int deleteRoom(int RoomId);

    int updateState_in(int RoomId);
    int updateState_out(int RoomId);
    int updateRoom(Room room);


}
