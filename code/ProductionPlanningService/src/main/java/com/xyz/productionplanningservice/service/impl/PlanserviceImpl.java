package com.xyz.productionplanningservice.service.impl;

import com.xyz.productionplanningservice.beans.Plan;
import com.xyz.productionplanningservice.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.xyz.productionplanningservice.util.datas.planDatabase;

@Service
public class PlanserviceImpl implements PlanService {


    @Override
    public Plan findPlanById(String id) {
        return planDatabase.get(id);
    }

    @Override
    public Plan createPlan(Map order, Map inventory, Map report, Map device) {
        //生成一个唯一id
        String id="123";
        /*对于order 订单，order里面有一个orderList，
        通过orderList我们可以知道所有客户需求的具体产品数量和期限要求
        这样基本可以确定startTime和endTime，也可以确定Plan.content里产品数量的基础*/
        int n=5;//这个代表订单列表里的订单数量;
        ArrayList<Double> need_product_num=new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            need_product_num.add(0.0); // 初始化每个元素为 0.0
        }
        String start_time="开始时间";
        String end_time="结束时间";
        Double product_sum=0.0;
        /*对于report 错误报告，从错误报告里我们可以得到产品合格率
        产品合格率可以作为一个参数作用到need_product_num上来得到需要生产的实际数量*/
        Double pass_rate=0.9;//假设合格率是0.9
        Double x=0.01;//这个x代表容错，应为不可能每次合格率都在0.9以上,具体数值可以变化
        for (int i=0;i<n;i++){
            need_product_num.set(i,need_product_num.get(i)/(pass_rate-x));
            product_sum+=need_product_num.get(i);
        }

        /*对于inventory 仓库，只需要确定现在有的原材料能不能支持创建所有product_sum
        * if(inventory.sum>product_xum){
        * //倘若inventory有一个sum表示所有原材料；
        * }
        * else{
        *   根据order的创建时间或者其他综合因数，把一些订单的status设定成“创建订单失败，原材料不足”
        * }
        * */

        /*对于device 设备，主要是判断在每一个订单的结束时间前能否生产出满足的product数量
           int today=0,end_day=10;
           double able_product=0;
           boolean flag=true；
           while( today<end_day){
                able_product+=日产量
                able_product-=在今天到期的订单需求量
                if(able_product<0){
                    flag=false;
                    break;
                }
           }
           if(!flag){
                根据order的创建时间或者其他综合因数，把一些订单的status设定成“创建订单失败，生产力不足”
           }
         */
        String content="具体内容";
        //对于device 设备，需要知道设备状况才能知道一天的产
        Plan plan=new Plan(id,content,start_time,end_time);
        planDatabase.put(plan.getId(),plan);
        return plan;
    }

    @Override
    public Plan improvePlan(String id, Map order,Map inventory,Map report,Map device) {
        Plan plan=planDatabase.get(id);
        //由于可以从原本的生产计划得到原有的订单信息，
        // 所以优化生产流程主要是根据现在新的订单信息、设备信息、质量报告和库存情况尝试接下更多订单和提早结束
        //比如修好了几台设备，那现在完成订单的时间可以变短
        String content="优化后的计划内容";
        plan.setContent(content);
        String endTime="优化后的结束时间";
        plan.setEndTime(endTime);
        return plan;
    }
}
