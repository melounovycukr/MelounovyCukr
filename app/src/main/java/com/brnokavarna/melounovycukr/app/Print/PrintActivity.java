package com.brnokavarna.melounovycukr.app.Print;

/**
 * Created by Me on 14.5.2014.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.starmicronics.stario.StarIOPort;
//import com.starmicronics.stario.StarIOPortException;
//import com.starmicronics.stario.StarPrinterStatus;
//import com.starmicronics.stario.PortInfo;
//import com.brnokavarna.melounovycukr.app.R;
import com.brnokavarna.melounovycukr.app.Exceptions.PrintException;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.starmicronics.stario.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Me on 14.5.2014.
 */



public class PrintActivity {


    // Setting for print
    private StarIOPort port = null;
    private String portName = "TCP:192.168.0.16";
    private String portSettings = "";
    private boolean sensorActiveHigh = true;
    private static int printableArea = 576;
    private boolean compressionEnable = true;
    private int source = com.brnokavarna.melounovycukr.app.R.drawable.logo_print2;
    private Context context;
    private Handler mHandler;
    private Calendar c;

    public PrintActivity(Context context, Handler mHandler) {

        this.context = context;
        this.mHandler = mHandler;
        this.c = Calendar.getInstance();
    }


    // GET PRINTER STATUS
    public void getPrinterStatus(){


        try
        {
            port = StarIOPort.getPort(portName, portSettings, 10000, context);

            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException e) {}

            StarPrinterStatus status = port.retreiveStatus();

            if(status.offline == false)
            {
                String message = "Printer is Online";

                if(status.compulsionSwitch == false)
                {
                    if (true == sensorActiveHigh) {
                        message += "\nCash Drawer: Close";
                    }
                    else {
                        message += "\nCash Drawer: Open";
                    }
                }
                else
                {
                    if (true == sensorActiveHigh) {
                        message += "\nCash Drawer: Open";
                    }
                    else {
                        message += "\nCash Drawer: Close";
                    }
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setNegativeButton("Ok", null);
                AlertDialog alert = dialog.create();
                alert.setTitle("Printer");
                alert.setMessage(message);
                alert.setCancelable(false);
                alert.show();
            }
            else
            {
                String message = "Printer is Offline";

                if(status.receiptPaperEmpty == true)
                {
                    message += "\nPaper is Empty";
                }

                if(status.coverOpen == true)
                {
                    message += "\nCover is Open";
                }

                if(status.compulsionSwitch == false)
                {
                    if (true == sensorActiveHigh) {
                        message += "\nCash Drawer: Close";
                    }
                    else {
                        message += "\nCash Drawer: Open";
                    }
                }
                else
                {
                    if (true == sensorActiveHigh) {
                        message += "\nCash Drawer: Open";
                    }
                    else {
                        message += "\nCash Drawer: Close";
                    }
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setNegativeButton("Ok", null);
                AlertDialog alert = dialog.create();
                alert.setTitle("Printer");
                alert.setMessage(message);
                alert.setCancelable(false);
                alert.show();
            }


        }
        catch (StarIOPortException e)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setNegativeButton("Ok", null);
            AlertDialog alert = dialog.create();
            alert.setTitle("Failure");
            alert.setMessage("Failed to connect to printer");
            alert.setCancelable(false);
            alert.show();
        }
        finally
        {
            if(port != null)
            {
                try {
                    StarIOPort.releasePort(port);
                } catch (StarIOPortException e) {}
            }
        }

    }


    // GET PRINTER FIRMWARE VERSION
    public void getPrinterFirmwareVersion(){

        try
        {
            port = StarIOPort.getPort(portName, portSettings, 10000, context);

            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException e) {}

            Map<String,String> firmware = port.getFirmwareInformation();

            String modelName = firmware.get("ModelName");
            String firmwareVersion = firmware.get("FirmwareVersion");


            String message = "Model Name:" + modelName;
            message += "\nFirmware Version:" + firmwareVersion;

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setNegativeButton("Ok", null);
            AlertDialog alert = dialog.create();
            alert.setTitle("Firmware Information");
            alert.setMessage(message);
            alert.setCancelable(false);
            alert.show();


        }
        catch (StarIOPortException e)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setNegativeButton("Ok", null);
            AlertDialog alert = dialog.create();
            alert.setTitle("Failure");
            alert.setMessage("Failed to connect to printer");
            alert.setCancelable(false);
            alert.show();
        }
        finally {
            if (port != null) {
                try {
                    StarIOPort.releasePort(port);
                } catch (StarIOPortException e) {
                }
            }
        }
    }


    // SEND COMMAND TO PRINTER
    private void sendCommand(final Context context, String portName, String portSettings, ArrayList<Byte> byteList) throws PrintException{

        try
        {
            port = StarIOPort.getPort(portName, portSettings, 10000, context);

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e) { }

            StarPrinterStatus status = port.beginCheckedBlock();

            if (true == status.offline)
            {
                throw new StarIOPortException("A printer is offline");
            }

            byte[] commandToSendToPrinter = convertFromListByteArrayTobyteArray(byteList);
            port.writePort(commandToSendToPrinter, 0, commandToSendToPrinter.length);

            port.setEndCheckedBlockTimeoutMillis(30000);//Change the timeout time of endCheckedBlock method.
            status = port.endCheckedBlock();

            if (true == status.coverOpen)
            {
                throw new StarIOPortException("Printer cover is open");
            }
            else if (true == status.receiptPaperEmpty)
            {
                throw new StarIOPortException("Receipt paper is empty");
            }
            else if (true == status.offline)
            {
                throw new StarIOPortException("Printer is offline");
            }
        }
        catch (final StarIOPortException e)
        {

            mHandler.post(new Runnable(){
                public void run(){

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setNegativeButton("Ok", null);
                    AlertDialog alert = dialog.create();
                    alert.setTitle("Failure");
                    alert.setMessage(e.getMessage());
                    alert.setCancelable(false);
                    alert.show();
                }
            });

            throw new PrintException(e);

        }
        finally
        {
            if (port != null)
            {
                try
                {
                    StarIOPort.releasePort(port);
                }
                catch (StarIOPortException e) {
                    System.err.println("\nCant release port!\n");
                }
            }
        }
    }


    // CREATE RASTER COMMNAD
    private static byte[] createRasterCommand(String printText, int textSize, int bold) {

        byte[] command;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        Typeface typeface;

        try {
            typeface = Typeface.create(Typeface.MONOSPACE, bold);
        } catch (Exception e) {
            typeface = Typeface.create(Typeface.DEFAULT, bold);
        }

        paint.setTypeface(typeface);
        paint.setTextSize(textSize * 2);
        paint.setLinearText(true);

        TextPaint textpaint = new TextPaint(paint);
        textpaint.setLinearText(true);
        StaticLayout staticLayout =  new StaticLayout(printText, textpaint, printableArea, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        int height = staticLayout.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(staticLayout.getWidth(), height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bitmap);
        c.drawColor(Color.WHITE);
        c.translate(0, 0);
        staticLayout.draw(c);

        StarBitmap starbitmap = new StarBitmap(bitmap, false, printableArea);

        command = starbitmap.getImageRasterDataForPrinting(true);

        return command;
    }


    // MAKE WHITE SPACES BEFORE
    private String getWhiteSpaceBefore(String name){

        String whiteSpaces = "";

        for(int i = name.length(); i < 26; i++){

            whiteSpaces += " ";

        }

        return whiteSpaces;
    }


    // MAKE WHITE SPACES AFTER
    private String getWhiteSpaceAfter(String amount, String price){

        int count;
        String whiteSpaces = "";
        if(price.length() >= 4){
            count = 39 - 26 - (int)Math.log10(Integer.parseInt(amount)) - (int)Math.log10(Integer.parseInt(price)) - 6;
        } else {

            count = 39 - 26 - (int) Math.log10(Integer.parseInt(amount)) - (int) Math.log10(Integer.parseInt(price)) - 5;
        }
        for(int i = 0; i < count; i++){

            whiteSpaces += " ";
        }

        return whiteSpaces;
    }

    String getWhiteSpacesTotal(int totalPrice){

        String whiteSpaces = "";
        int count = 0;

        if (((int)(Math.log10(totalPrice)+1) == 4) || ((int)(Math.log10(totalPrice)+1) == 5)){
            count = 34 - 15 - (int)(Math.log10(totalPrice)+1) - 4;
        } else if(((int)(Math.log10(totalPrice)+1) == 6)) {
            count = 34 - 15 - (int)(Math.log10(totalPrice)+1) - 5;
        }
        else {count = 34 - 15 - (int)(Math.log10(totalPrice)+1) - 3;}

        //int count = 34 - 15 - (int)(Math.log10(totalPrice)+1) - 3;

        for(int i = 0; i < count; i++){

            whiteSpaces += " ";
        }

        return whiteSpaces;
    }

    String getWhitespacesDate(int day,int month,int year,int hour,int minute){

        String whiteSpaces = "";

        int count = (39 - (int)(Math.log10(day)+1) - (int)(Math.log10(month)+1) - (int)(Math.log10(year)+1)
                - (int)(Math.log10(hour)+1) - (int)(Math.log10(minute)+1) - 8)/2;

        for(int i = 0; i < count; i++){

            whiteSpaces += " ";
        }

        return whiteSpaces;
    }


    // MAKE STRING OF ITEMS FOR RECIPE
    private String makeStringOfItems(ArrayList<HashMap<String, String>> allItemsListPerDay){

        String makeStringOfItems = "";

        Iterator<HashMap<String, String>> itr = allItemsListPerDay.iterator();
        while(itr.hasNext()) {
            HashMap<String, String> map = itr.next();
            String[] parts = map.get("price").split(" "); // to get rid of " Kč"
            String price = parts[0];

            String cenaUpravena = "";
            if(price.length() == 4) {
                String sub = price.substring(0,1);
                String suf = price.substring(1,4);
                cenaUpravena = sub + " " + suf;

            } else if (price.length() == 5) {
                String sub = price.substring(0,2);
                String suf = price.substring(2,5);
                cenaUpravena = sub + " " + suf;
            }

            if(cenaUpravena.equals("")){
                cenaUpravena = price;
            }

            makeStringOfItems += map.get("item") + getWhiteSpaceBefore(map.get("item")) + map.get("amount") + getWhiteSpaceAfter(map.get("amount"), price) + cenaUpravena + " Kč\n";
        }

        return makeStringOfItems;
    }

    // COUNT AND RETURN TOTAL PRIZE
    private int getTotalPrice(ArrayList<HashMap<String, String>> allItemsListPerDay){

        int totalprice = 0;

        Iterator<HashMap<String, String>> itr = allItemsListPerDay.iterator();
        while(itr.hasNext()) {
            HashMap<String, String> map = itr.next();
            String[] parts = map.get("price").split(" "); // to get rid of " Kč"
            String price = parts[0];
            totalprice += Integer.parseInt(price);
        }

        return totalprice;
    }



    //RASTER PRINT TABLE RECIPE
    public void printRecipePerTable(ArrayList<HashMap<String, String>> allItemsListPerTable) throws PrintException{

        Resources res = context.getResources();

        // Final list of bytes
        ArrayList<Byte> list = new ArrayList<Byte>();
        Byte[] tempList;

        // HeadLine print START
        RasterDocument rasterDocHeadLine = new RasterDocument(RasterDocument.RasSpeed.Medium, RasterDocument.RasPageEndMode.None, RasterDocument.RasPageEndMode.None, RasterDocument.RasTopMargin.Standard, 0, 0, 0);
        byte[] commandHeadLine = rasterDocHeadLine.BeginDocumentCommandData();
        tempList = new Byte[commandHeadLine.length];
        CopyArray(commandHeadLine, tempList);
        list.addAll(Arrays.asList(tempList));

        // Logo print START
        RasterDocument rasterDocImage = new RasterDocument(RasterDocument.RasSpeed.Medium, RasterDocument.RasPageEndMode.None, RasterDocument.RasPageEndMode.None, RasterDocument.RasTopMargin.Standard, 0, 0, 0);
        byte[] commandImage = rasterDocImage.BeginDocumentCommandData();
        tempList = new Byte[commandImage.length];
        CopyArray(commandImage, tempList);
        list.addAll(Arrays.asList(tempList));

        // Make image
        Bitmap bm = BitmapFactory.decodeResource(res, source);
        StarBitmap starbitmap = new StarBitmap(bm, false, printableArea);
        commandImage = starbitmap.getImageRasterDataForPrinting(compressionEnable);
        tempList = new Byte[commandImage.length];
        CopyArray(commandImage, tempList);
        list.addAll(Arrays.asList(tempList));

        // Logo print END
        commandImage = rasterDocImage.EndDocumentCommandData();
        tempList = new Byte[commandImage.length];
        CopyArray(commandImage, tempList);
        list.addAll(Arrays.asList(tempList));

        // Content print START
        RasterDocument rasterDocContent = new RasterDocument(RasterDocument.RasSpeed.Medium, RasterDocument.RasPageEndMode.FeedAndFullCut, RasterDocument.RasPageEndMode.FeedAndFullCut, RasterDocument.RasTopMargin.Standard, 0, 1, 0);
        byte[] commandContent = rasterDocContent.BeginDocumentCommandData();
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Begin speech
        String line_first = ("\n     Dnes jsme Vás pohostili tímto:\n\n---------------------------------------\n");
        commandContent = createRasterCommand(line_first, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        /*
        // Head of list
        String headList = ("NÁZEV                    KUSŮ      CENA\r\n");
        commandContent = createRasterCommand(headList, 12, Typeface.BOLD);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));
        */

        // List of items
        String stringOfItems = "";
        stringOfItems = makeStringOfItems(allItemsListPerTable);
        stringOfItems += "\n---------------------------------------\n";
        commandContent = createRasterCommand(stringOfItems, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Final price
        int tmp_price = getTotalPrice(allItemsListPerTable);
        String cena = Integer.toString(tmp_price);

        String cenaUpravena = "";
        if(cena.length() == 4) {
            String sub = cena.substring(0,1);
            String suf = cena.substring(1,4);
            cenaUpravena = sub + " " + suf;

        } else if (cena.length() == 5) {
            String sub = cena.substring(0,2);
            String suf = cena.substring(2,5);
            cenaUpravena = sub + " " + suf;
        }

        if(cenaUpravena.equals("")){
            cenaUpravena = cena;
        }
        String prize = ("Celkem to dělá:" + getWhiteSpacesTotal(tmp_price)+ cenaUpravena +" Kč");
        commandContent = createRasterCommand(prize, 14, 1);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Last line
        String line_last = ("\n\n   Děkujeme! A kdy se zase uvidíme?");
        commandContent = createRasterCommand(line_last, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Date
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        //String textToPrint = ("\nDatum: "+day+"."+month+"."+year+ getWhitespacesDate(day,month,year,hour,minute) + "Čas: "+hour+":"+minute+"\r\n");
        String textToPrint = ("\n"+ getWhitespacesDate(day,month,year,hour,minute) + day + ". " + month + ". " + year + " - " + hour + "." + (((int)(Math.log10(minute)+1))==1 ? "0"+minute : minute ) + "\r\n");
        commandContent = createRasterCommand(textToPrint, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Content print END
        commandContent = rasterDocContent.EndDocumentCommandData();
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));
        list.addAll(Arrays.asList(new Byte[]{0x07}));	// Kick cash drawer

        // Send ready list for print
        try{
            sendCommand(context, portName, portSettings, list);
        } catch(PrintException e){
            throw new PrintException(e);
        }


    }


    //RASTER PRINT DAY RECIPE
    public void printRecipePerDay(ArrayList<HashMap<String, String>> allItemsListPerDay) throws PrintException{

        Resources res = context.getResources();

        // Final list of bytes
        ArrayList<Byte> list = new ArrayList<Byte>();
        Byte[] tempList;

        // Content print START
        RasterDocument rasterDocContent = new RasterDocument(RasterDocument.RasSpeed.Medium, RasterDocument.RasPageEndMode.FeedAndFullCut, RasterDocument.RasPageEndMode.FeedAndFullCut, RasterDocument.RasTopMargin.Standard, 0, 0, 0);
        byte[] commandContent = rasterDocContent.BeginDocumentCommandData();
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Date
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String textToPrint = ("\nDatum: "+day+"."+month+"."+year+"            Čas: "+hour+":"+(((int)(Math.log10(minute)+1))==1 ? "0"+minute : minute )+"\r\n" +
                "---------------------------------------");
        commandContent = createRasterCommand(textToPrint, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Head of list
        String headList = ("NÁZEV                    KUSŮ      CENA\r\n");
        commandContent = createRasterCommand(headList, 12, Typeface.BOLD);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // List of items
        String stringOfItems = "";
        stringOfItems = makeStringOfItems(allItemsListPerDay);

        commandContent = createRasterCommand(stringOfItems, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Final price
        //String prize = ("---------------------------------------\nCelkem                         " + getTotalPrice(allItemsListPerDay) +" Kč");

        // Final price
        int tmp_price = getTotalPrice(allItemsListPerDay);
        String cena = Integer.toString(tmp_price);

        String cenaUpravena = "";
        if(cena.length() == 4) {
            String sub = cena.substring(0,1);
            String suf = cena.substring(1,4);
            cenaUpravena = sub + " " + suf;

        } else if (cena.length() == 5) {
            String sub = cena.substring(0,2);
            String suf = cena.substring(2,5);
            cenaUpravena = sub + " " + suf;
        } else if (cena.length() == 6) {
            String sub = cena.substring(0,3);
            String suf = cena.substring(3,6);
            cenaUpravena = sub + " " + suf;
        }

        if(cenaUpravena.equals("")){
            cenaUpravena = cena;
        }

        String prize = ("Celkem to dělá:" + getWhiteSpacesTotal(tmp_price)+ cenaUpravena +" Kč");
        commandContent = createRasterCommand(prize, 14, 1);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Content print END
        commandContent = rasterDocContent.EndDocumentCommandData();
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));
        list.addAll(Arrays.asList(new Byte[]{0x07}));	// Kick cash drawer

        // Send ready list for print
        sendCommand(context, portName, portSettings, list);

    }


    // FOR COPY byte[] to Byte[]
    private static void CopyArray(byte[] srcArray, Byte[] cpyArray) {
        for (int index = 0; index < cpyArray.length; index++) {
            cpyArray[index] = srcArray[index];
        }
    }

    // FOR COPY LIST BYTE TO byte[]
    private static byte[] convertFromListByteArrayTobyteArray(List<Byte> ByteArray)
    {
        byte[] byteArray = new byte[ByteArray.size()];
        for(int index = 0; index < byteArray.length; index++)
        {
            byteArray[index] = ByteArray.get(index);
        }

        return byteArray;
    }


}

