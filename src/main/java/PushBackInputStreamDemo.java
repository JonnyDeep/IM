import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class PushBackInputStreamDemo {
    public static void main(String[] args) throws IOException {
        String str = "hello,rolleholt";
        PushbackInputStream push = null;
        ByteArrayInputStream bat = null;
        bat = new ByteArrayInputStream(str.getBytes());
        push = new PushbackInputStream(bat);
        int temp=0;
        while ((temp = push.read())!= -1){
            if (temp ==','){
                push.unread(temp);
                temp = push.read();
                System.out.println("(回退" + (char) temp + ") ");
            }else{
                System.out.print((char) temp);
            }
        }
    }
}
