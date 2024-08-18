package demo.stpl.com.tpsmergedbuild.crashreport;

import android.util.Log;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;


public class GMailSender extends Authenticator {
    private String mailhost = "smtp.gmail.com";
    private String user;
    private Session session;
    private String password;
    private static GMailSender instance;
    public boolean isSendCalled = false;

//    static {
//        Security.addProvider(new com.example.stpl.crashgenerator.JSSEProvider());
//    }

    private GMailSender() {

    }

    //
    public static GMailSender getInstance() {
        return instance == null ? instance = new GMailSender() : instance;
    }

    //
    public void authUser(String user, String password, String exception) {
        if (!isSendCalled) {
            this.user = user;
            this.password = password;

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", mailhost);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");

            session = Session.getDefaultInstance(props, this);
            Log.v("seesion", session.toString());
            isSendCalled = true;


        }

    }

    //
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

//    public Thread thread;
    public synchronized void sendMail(final String subject, final String body, final String sender, final String recipients) throws Exception {

//        this.thread = thread;

        try {
            MimeMessage message = new MimeMessage(session);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setFrom(new InternetAddress(sender));
//            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(handler);
            if (recipients.indexOf(',') > 0)
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            Transport.send(message);
//            thread.interrupt();
        } catch (Exception e) {
//            thread.interrupt();
            e.getMessage();
        }

//        System.exit(0);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    MimeMessage message = new MimeMessage(session);
//                    DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
//                    message.setFrom(new InternetAddress(sender));
////            message.setSender(new InternetAddress(sender));
//                    message.setSubject(subject);
//                    message.setDataHandler(handler);
//                    if (recipients.indexOf(',') > 0)
//                        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//                    else
//                        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//                    Transport.send(message);
//                } catch (Exception e) {
//
//                    e.getMessage();
//                }
//
//
//            }
//        }).start();

    }
//
//    public class ByteArrayDataSource implements DataSource {
//        private byte[] data;
//        private String type;
//
//        public ByteArrayDataSource(byte[] data, String type) {
//            super();
//            this.data = data;
//            this.type = type;
//        }
//
//        public ByteArrayDataSource(byte[] data) {
//            super();
//            this.data = data;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public String getContentType() {
//            if (type == null)
//                return "application/octet-stream";
//            else
//                return type;
//        }
//
//        public InputStream getInputStream() throws IOException {
//            return new ByteArrayInputStream(data);
//        }
//
//        public String getName() {
//            return "ByteArrayDataSource";
//        }
//
//        public OutputStream getOutputStream() throws IOException {
//            throw new IOException("Not Supported");
//        }
//    }
}