package springboot.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.WyUploadDataMapper;
import springboot.model.WyUploadData;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class WyUploadDataService {
	@Autowired
	private WyUploadDataMapper wyUploadDataMapper; 
	
	public void add(WyUploadData wy){
		wyUploadDataMapper.add(wy);
	}
	
	public void delete(int id){
		wyUploadDataMapper.delete(id);
	}
	
	public List<WyUploadData> findWyUploadDataList(){
		return wyUploadDataMapper.findWyUploadDataList();
	}
	public void updateDelStatus(String ids){
		for(String id:ids.split(",")){
			wyUploadDataMapper.updateDelStatus("1",new Date(),Integer.parseInt(id));
		}
	}

	
	/**
     * 查询所有的person内容
     * @return
     */

    public Map<String,Object> getPageLog(){
        PageHelper.startPage(3 , 3);
        List<WyUploadData> list = wyUploadDataMapper.findWyUploadDataList();
        //得到分页的结果对象
        PageInfo<WyUploadData> personPageInfo = new PageInfo<WyUploadData>(list);
        //得到分页中的person条目对象
        List<WyUploadData> pageList = personPageInfo.getList();
        //将结果存入map进行传送
        Map map = new HashMap();
        map.put("pageInfo" , pageList);
        return map;
    }

}
