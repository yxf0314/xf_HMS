package com.xf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xf.dao.OrderMapper;
import com.xf.dao.RoomMapper;
import com.xf.dao.TypeMapper;
import com.xf.pojo.Order;
import com.xf.pojo.Room;
import com.xf.pojo.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoomController {

    @Autowired
    RoomMapper roomMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/rooms")//跳转到查询房间
    public String list(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Room> rooms = roomMapper.SelectAllRoom(null);
        PageInfo<Room> pageInfo = new PageInfo<Room>(rooms);//使用PageInfo类保存查询出来的rooms
        model.addAttribute("rooms",rooms);
        model.addAttribute("pageInfo",pageInfo);
        //System.out.println(pageInfo);

        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        return "room/list";
    }
    @GetMapping("/selectforbook")//入住页面的查询功能
    public String select(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam("TypeName")String TypeName,
                         @RequestParam(value = "Floor",required = false)Integer floor){
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("TypeName",TypeName);//多个不同类型的参数,采用HashMap存入并传给Mybatis
        map.put("Floor",floor);//注意,第一个参数是键名,与xml文件中对应
        PageHelper.startPage(pageNum,pageSize);
        List<Room> rooms = roomMapper.SelectAllRoom(map);
        PageInfo<Room> pageInfo = new PageInfo<Room>(rooms);//使用PageInfo类保存查询出来的rooms
        model.addAttribute("rooms",rooms);
        model.addAttribute("pageInfo",pageInfo);
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        return "room/book";
    }
    @GetMapping("/selectforlist")//查询页面的查询功能
    public String select2(Model model,@RequestParam("TypeName")String TypeName,
                          @RequestParam("Floor")Integer floor,
                          @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Map<String,Object> map2= new HashMap<String, Object>();
        map2.put("TypeName",TypeName);//多个不同类型的参数,采用HashMap存入并传给Mybatis
        map2.put("Floor",floor);//注意,第一个参数是键名,与xml文件中对应
        PageHelper.startPage(pageNum,pageSize);
        List<Room> rooms = roomMapper.SelectAllRoom(map2);
        PageInfo<Room> pageInfo = new PageInfo<Room>(rooms);//使用PageInfo类保存查询出来的rooms
        model.addAttribute("rooms",rooms);
        model.addAttribute("pageInfo",pageInfo);
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        return "room/list";
    }
    @GetMapping("/bookrooms")//跳转到入住页面
    public String book(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Room> rooms = roomMapper.SelectAllRoom(null);
        PageInfo<Room> pageInfo = new PageInfo<Room>(rooms);
        model.addAttribute("rooms",rooms);
        model.addAttribute("pageInfo",pageInfo);
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        return "room/book";
    }


    @GetMapping("/toAdd")
    public String toAddPage(Model model)
    {
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        return "room/add";
    }
    @PostMapping("/add")
    public String AddRoom(@RequestParam("TypeName")String TypeName,
                          @RequestParam("RoomId")int RoomId,
                          @RequestParam("Floor")int floor)
    {
        //System.out.println(room);
        roomMapper.addRoom(new Room(TypeName,RoomId,floor,"可入住"));
        return "redirect:/rooms";
    }


    @GetMapping("/delete/{RoomId}")
    public String DeleteRoom(@PathVariable("RoomId")int RoomId)
    {
        roomMapper.deleteRoom(RoomId);
        System.out.println("删除了:"+RoomId);
        return "redirect:/rooms";
    }


    @GetMapping("/toUpdate/{RoomId}")
    public String toUpdate(@PathVariable("RoomId")int RoomId,Model model)
    {
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        Room room = roomMapper.ID_Select(RoomId);
        model.addAttribute("room",room);
        return "room/update";
    }
    @PostMapping("/update")
    public String update(@RequestParam("TypeName")String TypeName,
                         @RequestParam("RoomId")int RoomId,
                         @RequestParam("floor")int floor,
                         @RequestParam("state")String state)
    {
        roomMapper.updateRoom(new Room(TypeName,RoomId,floor,state));
        return "redirect:/rooms";
    }

    @GetMapping("/live_in/{RoomId}")
    public String LiveInRoom(@PathVariable("RoomId") int RoomId)
    {
        roomMapper.updateState_in(RoomId);
        System.out.println(RoomId+"入住成功");
        return "redirect:/bookrooms";
    }

    @GetMapping("/live_out/{RoomId}")
    public String LiveOutRoom(@PathVariable("RoomId") int RoomId)
    {
        roomMapper.updateState_out(RoomId);
        System.out.println(RoomId+"退房成功");
        return "redirect:/bookrooms";
    }

    @GetMapping("/selectfororders")//跳转到预订订单
    public String selectorder(Model model, @RequestParam("TypeName")String typename,
                              @RequestParam("gname")String gname,
                              @RequestParam("gphone")String gphone){
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("typename",typename);
        map.put("gname",gname);
        map.put("gphone",gphone);
        Collection<Order> orders = orderMapper.SelectOrder(map);
        model.addAttribute("orders",orders);
        return "room/order";
    }
    @GetMapping("/orders")//跳转到预订订单
    public String order(Model model){
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        Collection<Order> orders = orderMapper.SelectOrder(null);
        model.addAttribute("orders",orders);
        return "room/order";
    }

    @RequestMapping("/booknow")
    public String booknow(Model model,
                          @RequestParam("indate")String indate,
                          @RequestParam("outdate")String outdate,
                          @RequestParam("typename")String typename,
                          @RequestParam("gphone")String gphone,
                          @RequestParam("gname")String gname)
    {

        Order order = new Order(gname,typename,gphone,indate,outdate);
        System.out.println(order);
        int success = orderMapper.AddOrder(order);
        System.out.println(success);
        return "redirect:/";
    }

    @RequestMapping("/leastroom")
    @ResponseBody
    public int least(String typename){
        int least = orderMapper.leastroom(typename);
        System.out.println(least);
        return least;
    }

    @RequestMapping("/checklive")
    public String check(@RequestParam("id")Integer id,
                        @RequestParam("typename")String typename,
                        RedirectAttributes model){
        orderMapper.checkorder(id);
        model.addAttribute("TypeName",typename);
        return "redirect:/selectforcheck";
    }
    @RequestMapping("/selectforcheck")//入住页面的查询功能
    public String checkin(Model model,
                          @RequestParam("TypeName")String TypeName,
                          @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("TypeName",TypeName);//多个不同类型的参数,采用HashMap存入并传给Mybatis
        PageHelper.startPage(pageNum,pageSize);
        List<Room> rooms = roomMapper.SelectAllRoom(map);
        PageInfo<Room> pageInfo = new PageInfo<Room>(rooms);
        model.addAttribute("rooms",rooms);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println(rooms);
        Collection<RoomType> typelist = typeMapper.SelectTypeList();//把房间类型查找出来传给下一个页面的选择框
        model.addAttribute("typelist",typelist);
        return "room/book";
    }

}
