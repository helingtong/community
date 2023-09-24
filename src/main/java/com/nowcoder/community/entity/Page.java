package com.nowcoder.community.entity;
/*用于封装分页相关的信息 条件*/
public class Page {
//    当前页码
    private int current = 1;
//    显示上线
    private int limit = 10;
//    计算数据总数 一共多少行 表里多少数据除以limit
    private int rows;
//    查询路径（用于复用分页链接）
    private String path;
//因为这些都是私有的变量，所以需要set get方法才可以访问这些变量
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){ //如果用户将条件写成了0 负数
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
//        限制显示的条数最多是100 太多对服务器就造成了很大压力容易卡
        if(limit>=1 && limit<=100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    /*获取当前页的起始行
    @return
    */
    public int getOffset(){
        return (current -1) * limit;
    }
    /*获取总页数*/
    public int getTotal(){
//        rows/limit 总行数除每一页显示的条数
        if(rows % limit == 0){
            return rows/limit;
        }else {
            return rows/limit + 1;
        }
    }
    /*获取起始页码
    * */
    public int getFrom(){
        int from = current -2;
        return from < 1 ? 1 : from;
    }
    /*获取结束页码
     * */
    public int getTo(){
        int to = current +2;
//        当前页就是100了 + 2就超过了，这个时候就不用加2
        int total = getTotal();
        return to > total ? total :to;
    }
}
