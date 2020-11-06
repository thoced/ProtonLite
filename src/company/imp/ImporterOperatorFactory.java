package company.imp;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * @class class factory d'import de base des opérateurs téléphoniques
 * @author thonon cedric
 */

public class ImporterOperatorFactory {

    private static ImporterOperatorFactory ourInstance = new ImporterOperatorFactory();

    public static ImporterOperatorFactory getInstance() {
        return ourInstance;
    }

    private enum OPERATORS {ORANGE,PROXIMUS,TELENET,NONE};

    private OPERATORS operatorSelected = OPERATORS.NONE;

    private ImporterOperatorFactory() {
    }

    public OperatorParser getImporterParser(String fileNameOperator) throws OperatorException,IOException,InvalidFormatException{
        if(fileNameOperator == null)
            throw new OperatorException("le nom du fichier ou le File d'escripteur de l'opérateur ne peut est NULL");

        File file = new File(fileNameOperator);

        return getImporterParser(file);
    }

    public OperatorParser getImporterParser(File fileOperator) throws OperatorException, IOException, InvalidFormatException {
        if(fileOperator == null)
            throw new OperatorException("le nom du fichier ou le File d'escripteur de l'opérateur ne peut est NULL");


        // chargement du fichier
        OPCPackage pkg = OPCPackage.open(fileOperator);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        pkg.close();

        // le fichier est bien chargé, nous déterminons de quel opérateur il provient

        // réception des feuilles
        int nbSheet = wb.getNumberOfSheets();
        operatorSelected = OPERATORS.NONE;

        // fichier Orange
        if(nbSheet == 2  && wb.getSheetName(0).startsWith("Traffic In") && wb.getSheetName(1).startsWith("Glossary")){
            System.out.println("C'est un fichier Orange");
            operatorSelected = OPERATORS.ORANGE;
            return new Orange(wb);
        }

        if(nbSheet == 4 && wb.getSheetName(1).startsWith("CALLS")){
            System.out.println("C'est un fichier Telenet");
            operatorSelected = OPERATORS.TELENET;
            return null;
        }

        // fichier Telenet
        if(nbSheet > 2 && wb.getSheetName(0).startsWith("Front") && wb.getSheetName(1).startsWith("Calls for")){
            System.out.println("C'est un fichier Proximus");
            operatorSelected = OPERATORS.PROXIMUS;
            return null;
        }

        throw new OperatorException("Operateur téléphonique non trouvé, vérifiez l'authenticité du fichier");


    }


}
