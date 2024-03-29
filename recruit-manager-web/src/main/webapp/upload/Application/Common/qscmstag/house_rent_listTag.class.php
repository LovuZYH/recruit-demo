<?php
/**
 * 出租
 */
namespace Common\qscmstag;
defined('THINK_PATH') or exit();
class house_rent_listTag {
	protected $params = array();
	protected $map = array();
	protected $order;
	protected $limit;
    function __construct($options) {
    	$array = array(
    		'列表名'			=>	'listname',
    		'显示数目'			=>	'row',
    		'标题长度'			=>	'titlelen',
    		'开始位置'			=>	'start',
    		'填补字符'			=>	'dot',
            '房屋厅室'          =>  'house',
            '月租金'            =>  'rent',
            '地区分类'          =>  'citycategory',
            '关键字'            =>  'key',
            '分页显示'          =>  'paged',
    		'排序'				=>	'displayorder',
            '描述长度'          =>  'brieflylen',
            '去除id'            =>  'except_id',
    	);
    	foreach ($options as $key => $value) {
    		$this->params[$array[$key]] = $value;
    	}
        if(isset($this->params['house']) && intval($this->params['house'])>0){
            $this->map['house'] = array('eq',intval($this->params['house']));
        }
        if ($this->params['rent']) {
            if (stripos($this->params['rent'], '-') === false) {
                $this->map['rent'] = array('egt', $this->params['rent']);
            } else {
                $sub_rent_arr = explode("-", $this->params['rent']);
                $this->map['rent'] = array(array('egt', $sub_rent_arr[0]), array('lt', $sub_rent_arr[1]), 'and');
            }
        }
        if(!$this->params['uid'] && C('SUBSITE_VAL.s_id') > 0 && !$this->params['citycategory']){
            $this->params['citycategory'] = C('SUBSITE_VAL.s_district');
            $this->is_subsite = 1;
        }
        $this->_where_district($this->params['citycategory']);
        if(isset($this->params['key']) && trim($this->params['key'])<>''){
            $this->map['key'] = array('like','%'.trim($this->params['key']).'%');
        }
        $this->params['except_id'] && $this->map['id'] = array('neq',intval($this->params['except_id']));
        $this->order = 'refreshtime desc,id desc';
    	$this->limit = isset($this->params['row'])?intval($this->params['row']):10;
    	$this->params['listname']=isset($this->params['listname'])?$this->params['listname']:"list";
		$this->params['start']=isset($this->params['start'])?intval($this->params['start']):0;
		$this->params['titlelen']=isset($this->params['titlelen'])?intval($this->params['titlelen']):15;
		$this->params['dot']=isset($this->params['dot'])?$this->params['dot']:'';
    }
    public function run(){
    	if($this->params['paged']){
    		$count = M('HouseRentSearch')->where($this->map);
	    	$total = $count->count();
	        $pager = pager($total, $this->limit);
            $pager->showname = 'QS_house_rent';
	        $page = $pager->fshow();
	        $this->params['start']>0 && $pager->firstRow = $this->params['start'];
	        $this->limit = $pager->firstRow.','.$pager->listRows;
    	}else{
            $this->limit = $this->params['start'].','.$this->limit;
    		$total = 0;
    		$page = '';
    	}
        $ids = M('HouseRentSearch')->where($this->map)->limit($this->limit)->order($this->order)->getField('id', true);
        if ($ids) {
            $list = D('House/HouseRent')->where(array('id' => array('in', $ids)))->order('refreshtime desc,id desc')->select();
        } else {
            $list = array();
        }
        foreach ($list as $key => $val) {
            $subsite_id = get_jobs_subsite_id($val);
            $val['url']=url_rewrite('QS_house_rent_show',array('id'=>$val['id']),$subsite_id);
            if ($this->params['brieflylen']>0){
                $val['briefly']=cut_str(strip_tags($val['contents']),$this->params['brieflylen'],0,$this->params['dot']);
            }else{
                $val['briefly']=strip_tags($val['contents']);
            }
            $val['refreshtime_cn'] = daterange(time(),$val['refreshtime'],'Y-m-d');
            $list[$key] = $val;
        }
        $return['page'] = $page;
        $return['total'] = $total;
        $return['list'] = $list;
        return $return;
    }
    private function _where_district($data){
        if($data){
            if (!strpos($data,".")){
                if(APP_SPELL && !fieldRegex($data,'in')){
                    $result = D('CategoryDistrict')->city_cate_cache();
                    $arr=explode(",",$data);
                    foreach ($arr as $key => $val) {
                        $arr[$key] = $result['spell'][$val]['id'];
                    }
                }else{
                    if(fieldRegex($data,'in')){
                        $arr=explode(",",$data);
                    }
                }
                $arr=array_unique($arr);
                if($arr){
                    if(false === $city_cate = F('city_search_cate')) $city_cate = D('CategoryDistrict')->city_search_cache();
                    foreach ($arr as $key => $val) {
                        if(C('SUBSITE_VAL.s_id') > 0){
                            $reg = strpos(C('SUBSITE_VAL.s_district'),',') ? '/^\_'.str_replace(',','|',C('SUBSITE_VAL.s_district')).'\_/' : '_'.C('SUBSITE_VAL.s_district').'_';
                            if(preg_match($reg,'_'.$city_cate[$val].'_') !== 1) continue;
                        }
                        $s[] = str_replace('_','.',$city_cate[$val]);
                    }
                }
                $s = $s[0];
                $s_arr = explode(".", $s);
                $s_arr = array_filter($s_arr);
                $district_id = implode(".", $s_arr);
            }else{
                $district_id = $data;
            }
            if ($district_id) {
                // $district_info = get_city_info($district);
                // $district_id = $district_info['district'];
                $district_id_arr = explode(".", $district_id);
                $this->map['district' . count($district_id_arr)] = array('eq', $district_id);
            } else {
                if (C('SUBSITE_VAL.s_id') > 0) {
                    $district_info = get_city_info(C('SUBSITE_VAL.s_district'));
                    $district_id = $district_info['district'];
                    $district_id_arr = explode(".", $district_id);
                    $this->map['district' . count($district_id_arr)] = array('eq', $district_id);
                }
            }
        }
    }
}