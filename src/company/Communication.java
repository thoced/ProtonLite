package company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
public class Communication {

    @Id @GeneratedValue
    private long id;
    private String sender;
    private String receiver;
    private Timestamp start;
    private Timestamp end;
    private String identSender;
    private String identReceiver;
    private String adressSender;
    private String adrsseReceiver;
    private String btsFirstAdress;
    private String btsLastAdress;
    private String duration; // peut être une durée ou du texte (SMS, DATA, ETC)
    private String CallType;  // In ou Out

    public Communication() {
    }

    public Communication(String sender, String receiver, Timestamp start, Timestamp end) {
        this.sender = sender;
        this.receiver = receiver;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getIdentSender() {
        return identSender;
    }

    public void setIdentSender(String identSender) {
        this.identSender = identSender;
    }

    public String getIdentReceiver() {
        return identReceiver;
    }

    public void setIdentReceiver(String identReceiver) {
        this.identReceiver = identReceiver;
    }

    public String getAdressSender() {
        return adressSender;
    }

    public void setAdressSender(String adressSender) {
        this.adressSender = adressSender;
    }

    public String getAdrsseReceiver() {
        return adrsseReceiver;
    }

    public void setAdrsseReceiver(String adrsseReceiver) {
        this.adrsseReceiver = adrsseReceiver;
    }

    public String getBtsFirstAdress() {
        return btsFirstAdress;
    }

    public void setBtsFirstAdress(String btsFirstAdress) {
        this.btsFirstAdress = btsFirstAdress;
    }

    public String getBtsLastAdress() {
        return btsLastAdress;
    }

    public void setBtsLastAdress(String btsLastAdress) {
        this.btsLastAdress = btsLastAdress;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCallType() {
        return CallType;
    }

    public void setCallType(String callType) {
        CallType = callType;
    }

    @Override
    public String toString() {
        return "Communication{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", identSender='" + identSender + '\'' +
                ", identReceiver='" + identReceiver + '\'' +
                ", adressSender='" + adressSender + '\'' +
                ", adrsseReceiver='" + adrsseReceiver + '\'' +
                ", btsFirstAdress='" + btsFirstAdress + '\'' +
                ", btsLastAdress='" + btsLastAdress + '\'' +
                ", duration='" + duration + '\'' +
                ", CallType='" + CallType + '\'' +
                '}';
    }
}
