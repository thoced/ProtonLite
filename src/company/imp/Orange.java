package company.imp;

import company.Communication;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Orange extends OperatorParser {

    public enum LABELS {StartSession,EndSession,Sender,IdentSender};

    public Orange(XSSFWorkbook wb) {
        super(wb);
    }

    @Override
    public String getNameOperator() {
        return "ORANGE";
    }

    @Override
    public long getNbCommunications() {
        return 0;
    }

    @Override
    public List<Communication> getCommunications() throws OperatorException {
        XSSFSheet sheet = wb.getSheetAt(0);

        // recherche de la première ligne dans le fichier Orange
        XSSFRow rowLabels = getRowLabels(sheet);
        if (rowLabels == null)
            throw new OperatorException("Pas de ligne contenant le labels des colonnes dans le fichier Orange");

        int numFirstRow = rowLabels.getRowNum() + 1;
        int lastRow = sheet.getLastRowNum();

        int firstIndCell = rowLabels.getFirstCellNum();
        int lastIndCell = rowLabels.getLastCellNum();

        List<Communication> listCommunications = new ArrayList<Communication>();
        for (int i = numFirstRow; i <= lastRow; i++) {
            Communication communication = new Communication();
            XSSFCell start = sheet.getRow(i).getCell(LABELS.StartSession.ordinal());
            XSSFCell end = sheet.getRow(i).getCell(LABELS.EndSession.ordinal());
            XSSFCell sender = sheet.getRow(i).getCell(LABELS.Sender.ordinal());
            XSSFCell identSender = sheet.getRow(i).getCell(6); // IdentSender
            XSSFCell identSenderFirstName = sheet.getRow(i).getCell(7); // IdentSender

            XSSFCell adresseSender = sheet.getRow(i).getCell(8); // adresse
            XSSFCell streetNumberSender = sheet.getRow(i).getCell(9); // streetNumber
            XSSFCell zipCodeSender = sheet.getRow(i).getCell(10); // zipCode
            XSSFCell townSender = sheet.getRow(i).getCell(11); // town

            XSSFCell bts = sheet.getRow(i).getCell(13); // bts
            XSSFCell btsLast = sheet.getRow(i).getCell(18); // btsLast

            XSSFCell receiver = sheet.getRow(i).getCell(22); // receiver
            XSSFCell identReceiver = sheet.getRow(i).getCell(26); // IdentSender
            XSSFCell identReceiverFirstName = sheet.getRow(i).getCell(27); // IdentSender

            XSSFCell adresseReceiver = sheet.getRow(i).getCell(28); // adresse
            XSSFCell streetNumberReceiver = sheet.getRow(i).getCell(29); // streetNumber
            XSSFCell zipCodeReceiver = sheet.getRow(i).getCell(30); // zipCode
            XSSFCell townReceiver = sheet.getRow(i).getCell(31); // town

            XSSFCell duraction = sheet.getRow(i).getCell(32); // duration

            // start
            try {
                communication.setStart(java.sql.Timestamp.valueOf(LocalDateTime.parse(start.getStringCellValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            }catch(DateTimeParseException dtpe){
                communication.setStart(null);
            }
            // end
            try {
                communication.setEnd(java.sql.Timestamp.valueOf(LocalDateTime.parse(end.getStringCellValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            }
            catch (DateTimeParseException dtpe){
                communication.setEnd(null);
            }
            // sender
                communication.setSender(sender.getStringCellValue());
            // IdentSender
                communication.setIdentSender(identSender.getStringCellValue() + " " + identSenderFirstName.getStringCellValue());
            // adresseSender
                communication.setAdressSender(adresseSender.getStringCellValue() + " " + streetNumberSender.getStringCellValue() + " " + zipCodeSender.getStringCellValue() + " " + townSender.getStringCellValue());
            // bts
                communication.setBtsFirstAdress(bts.getStringCellValue());
            // btsLast
                communication.setBtsLastAdress(btsLast.getStringCellValue());

            // receiver
                communication.setReceiver(receiver.getStringCellValue());
            // IdentReceiver
                communication.setIdentReceiver(identReceiver.getStringCellValue() + " " + identReceiverFirstName.getStringCellValue());
            // adresseReceiver
                communication.setAdressSender(adresseReceiver.getStringCellValue() + " " + streetNumberReceiver.getStringCellValue() + " " + zipCodeReceiver.getStringCellValue() + " " + townReceiver.getStringCellValue());
            // duration
                communication.setDuration(duraction.getStringCellValue());

           // ajout dans la list
            listCommunications.add(communication);
        }

        return listCommunications;

}

    private XSSFRow getRowLabels(XSSFSheet sheet){
        int first = sheet.getFirstRowNum();
        int last = sheet.getLastRowNum();
        for(int i=first; i < last ; i++){
            XSSFRow currentRow = sheet.getRow(i);
            if(currentRow != null){
                XSSFCell cell = currentRow.getCell(0);
                if(cell != null && cell.getCellType() == CellType.STRING){
                    if(cell.getStringCellValue().startsWith("Start Session")){
                        return currentRow;
                    }

                }
            }
        }
        return null;
    }
}