package company.imp;


import company.Communication;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public abstract class OperatorParser {

    protected final XSSFWorkbook wb;

    public OperatorParser(XSSFWorkbook wb){

        this.wb = wb;
    }
    public abstract String getNameOperator();
    public abstract long getNbCommunications();
    public abstract List<Communication> getCommunications() throws OperatorException;

}
