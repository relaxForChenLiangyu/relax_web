package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper {
    @Delete({
        "delete from order",
        "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer orderId);

    @Insert({
        "insert into order (order_id, patient_id, ",
        "specialist_id, type_id, ",
        "order_status, consulting_way, ",
        "publish_time, complete_time, ",
        "sum, location, description, consulting_start_time, consulting_finish_time)",
        "values (#{orderId,jdbcType=INTEGER}, #{patientId,jdbcType=INTEGER}, ",
        "#{specialistId,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{orderStatus,jdbcType=INTEGER}, #{consultingWay,jdbcType=INTEGER}, ",
        "#{publishTime,jdbcType=TIMESTAMP}, #{completeTime,jdbcType=TIMESTAMP}, ",
        "#{sum,jdbcType=DOUBLE}, #{location,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{consultingStartTime,jdbcType=TIMESTAMP},#{consultingFinishTime,jdbcType=TIMESTAMP},)"
    })
    int insert(Order record);

    int insertSelective(Order record);

    @Select({
        "select",
        "order_id, patient_id, specialist_id, type_id, order_status, consulting_way, ",
        "publish_time, complete_time, sum, location, description, consulting_start_time, consulting_finish_time",
        "from `order`",
        "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    @Update({
        "update order",
        "set patient_id = #{patientId,jdbcType=INTEGER},",
          "specialist_id = #{specialistId,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "order_status = #{orderStatus,jdbcType=INTEGER},",
          "consulting_way = #{consultingWay,jdbcType=INTEGER},",
          "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
          "complete_time = #{completeTime,jdbcType=TIMESTAMP},",
          "sum = #{sum,jdbcType=DOUBLE},",
          "location = #{location,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
          "consulting_start_time = #{consultingStartTime,jdbcType=TIMESTAMP}",
          "consulting_finish_time =#{consultingFinishTime,jdbcType=TIMESTAMP}",
        "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);
  /*<select id="getByNumber" resultType="com.ecnu.relax.model.Order">
    select * from `order` where patient_id = #{userId,jdbcType=INTEGER}
    limit #{start,jdbcType=INTEGER},#{size,jdbcType=INTEGER}
  </select>
  <select id="getByNumber1" resultType="com.ecnu.relax.model.Order">
    select * from `order` where specialist_id = #{specialistId,jdbcType=INTEGER}
    limit #{start,jdbcType=INTEGER},#{size,jdbcType=INTEGER}
  </select>*/
  @Select({
          "select * ",
          "from `order`",
          "where patient_id = #{userId} order by publish_time desc"
  })
  @ResultMap("BaseResultMap")
    List<Order> getByNumber (Integer userId, RowBounds rowBounds);

    @Select({
            "select * ",
            "from `order`",
            "where  specialist_id = #{specialistId} order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> getByNumber1 (Integer specialistId,RowBounds rowBounds);

    @Update({
            "update `order`",
            "set order_status = #{arg1}",
            "where order_id = #{arg0}"
    })
    int changeStatusByPrimaryKey(Integer orderId,Integer orderStatus);
}