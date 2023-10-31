package com.jc.campusemploydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.CollectionDetail;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Collection;
import com.jc.campusemploydemo.domain.Positions;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.mapper.CollectionMapper;
import com.jc.campusemploydemo.mapper.PositionsMapper;
import com.jc.campusemploydemo.mapper.UserMapper;
import com.jc.campusemploydemo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private PositionsMapper positionsMapper;
    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private UserMapper userMapper;

    // 用户-职位收藏关系矩阵
    private int[][] matrix;

    /**
     * 查询用户是否收藏该岗位
     * @param uid
     * @param pid
     * @return
     */
    @Override
    public Result findCollection(Integer uid, Integer pid) {
        Integer uidAndPid = positionsMapper.findCollectUidAndPid(uid, pid);
        if (uidAndPid == 0 ){
            return new Result(false,"没有收藏");
        }
        return new Result(true,"已收藏");
    }

    @Override
    public void addCollection(Integer uid, Integer pid) {
        Positions positions = positionsMapper.selectById(pid);
        positionsMapper.addCollection(uid,pid,positions.getPFlag());
    }

    @Override
    public void deleteCollection(Integer uid, Integer id) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("user_id",uid);
        collectionMapper.delete(wrapper);
    }

    @Override
    public List<CollectionDetail> showAllCollection(Integer uid, Integer page, Integer size) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",uid);
        List<Collection> list = collectionMapper.selectList(queryWrapper);

        List<CollectionDetail> detailList = new ArrayList<>();
        Integer count = Math.toIntExact(collectionMapper.selectCount(queryWrapper));
        for (Collection temp : list) {
            Positions p = positionsMapper.selectByPrimaryKey(temp.getPId());
            CollectionDetail col = new CollectionDetail(temp.getId(),count,p.getPName(),p.getPClassify(),p.getPDescribe(),p.getPResponsibility()
                    , p.getPRequest(),p.getPWorkSite(),p.getPDepartment(),p.getPFlag());
            detailList.add(col);
        }

        return detailList;
    }

    /**
     * 给用户推荐职位
     *
     * @param uid
     * @param k
     * @return
     */
    @Override
    public Result recommendKPosition(Integer uid, Integer k) {
        initMatrix();
        List<Integer> list = recommend(uid, k);
        List<Positions> positions = positionsMapper.selectBatchIds(list);
        return new Result(true,"推荐成功",positions);
    }

//        推荐算法实现

    /**
     * similarity方法通过余弦相似度计算职位之间的相似度
      * @param p1
     * @param p2
     * @return
     */
    private double similarity(int p1, int p2) {
        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][p1] > 0 && matrix[i][p2] > 0) {
                sum1 += matrix[i][p1] * matrix[i][p2];
                sum2 += matrix[i][p1] * matrix[i][p1];
                sum3 += matrix[i][p2] * matrix[i][p2];
            }
        }
        double sim = sum1 / (Math.sqrt(sum2) * Math.sqrt(sum3));
        return sim;
    }

    /**
     * recommend方法根据用户的收藏情况和职位之间的相似度计算推荐得分，并返回前k个得分最高的职位
     * @param userId
     * @param k
     * @return
     */
    private List<Integer> recommend(int userId, int k) {
        List<Integer> items = new ArrayList<>();
        Map<Integer, Double> scores = new HashMap<>();
        for (int i = 1; i < matrix[1].length; i++) {
            if (matrix[userId][i] == 0) {
                double score = 0;
                for (int j = 1; j < matrix[1].length; j++) {
                    if (matrix[userId][j] > 0) {
                        double sim = similarity(i, j);
                        score += sim * matrix[userId][j];
                    }
                }
                scores.put(i, score);
            }
        }


        // 对推荐职位按照得分进行排序
        List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer, Double>>(scores.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // 返回前k个得分最高的职位
        for (int i = 0; i < k; i++) {
            items.add(list.get(i).getKey());
        }
        return items;
    }
    private void initMatrix(){
        QueryWrapper<Positions> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        Positions positions = positionsMapper.selectOne(wrapper);
        System.out.println("zwei"+positions);
        matrix = new int[userMapper.showUserCount()+1][positions.getId()+1];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i],0);
        }
        List<Collection> list = collectionMapper.selectList(null);
        for (Collection collection : list) {
            matrix[collection.getUserId()][collection.getPId()] = 1;
        }
    }


}
