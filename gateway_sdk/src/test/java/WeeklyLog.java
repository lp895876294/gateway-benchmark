import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

/**
 * @author toquery
 * @version 1
 */
//附件类
class  Attachment implements Serializable
{
    private  String name; //附件名
    public  void setName(String name)
    {
        this.name  = name;
    }
    public  String getName()
    {
        return  this.name;
    }
    public void download()
    {
        System.out.println("下载附件，文件名为" + name);
    }
}
class WeeklyLog implements Cloneable,Serializable
{
    private  Attachment attachment;
    private  String name;
    private  String date;
    private  String content;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public  void setName(String name) {
        this.name  = name;
    }
    public  void setDate(String date) {
        this.date  = date;
    }
    public  void setContent(String content) {
        this.content  = content;
    }
    public  String getName() {
        return  (this.name);
    }
    public  String getDate() {
        return  (this.date);
    }
    public  String getContent() {
        return  (this.content);
    }
    //克隆方法clone()，此处使用Java语言提供的克隆机制
    public WeeklyLog clone()
    {
        Object obj = null;
        try
        {
            obj = super.clone();
            return (WeeklyLog)obj;
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println("不支持复制！");
            return null;
        }
    }

    //使用序列化技术实现深克隆
    public WeeklyLog deepClone() throws IOException, ClassNotFoundException, OptionalDataException
    {
        //将对象写入流中
        ByteArrayOutputStream bao=new  ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bao);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream bis=new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bis);
        return  (WeeklyLog)ois.readObject();
    }
}

class Client
{
    public  static void main(String args[])
    {
        /*
        WeeklyLog log_previous = new WeeklyLog();  //创建原型对象
        log_previous.setName("张无忌");
        log_previous.setDate("第12周");
        log_previous.setContent("这周工作很忙，每天加班！");

        System.out.println("****周报****");
        System.out.println("周次：" +  log_previous.getDate());
        System.out.println("姓名：" +  log_previous.getName());
        System.out.println("内容：" +  log_previous.getContent());
        System.out.println("--------------------------------");

        WeeklyLog  log_new;
        log_new  = log_previous.clone(); //调用克隆方法创建克隆对象
        log_new.setDate("第13周");
        System.out.println("****周报****");
        System.out.println("周次：" + log_new.getDate());
        System.out.println("姓名：" + log_new.getName());
        System.out.println("内容：" + log_new.getContent());


        System.out.println(log_previous == log_new);
        System.out.println(log_previous.getDate() == log_new.getDate());
        System.out.println(log_previous.getName() == log_new.getName());
        System.out.println(log_previous.getContent() == log_new.getContent());
        */

        WeeklyLog  log_previous, log_new = null;
        log_previous  = new WeeklyLog(); //创建原型对象
        log_previous.setName("张无忌");
        log_previous.setDate("第12周");
        log_previous.setContent("这周工作很忙，每天加班！");
        Attachment  attachment = new Attachment(); //创建附件对象
        log_previous.setAttachment(attachment);  //将附件添加到周报中
        try
        {
            log_new =  log_previous.deepClone(); //调用深克隆方法创建克隆对象
        }
        catch(Exception e)
        {
            System.err.println("克隆失败！");
        }
        //比较周报
        System.out.println("周报是否相同？ " + (log_previous ==  log_new));
        //比较附件
        System.out.println("附件是否相同？ " +  (log_previous.getAttachment() == log_new.getAttachment()));
        System.out.println(log_previous == log_new);
        System.out.println(log_previous.getDate() == log_new.getDate());
        System.out.println(log_previous.getName() == log_new.getName());
        System.out.println(log_previous.getContent() == log_new.getContent());
    }
}
