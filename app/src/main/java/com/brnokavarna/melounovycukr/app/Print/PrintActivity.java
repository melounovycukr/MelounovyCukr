package com.brnokavarna.melounovycukr.app.Print;

/**
 * Created by Me on 14.5.2014.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.starmicronics.stario.StarIOPort;
import com.starmicronics.stario.StarIOPortException;
import com.starmicronics.stario.StarPrinterStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Me on 14.5.2014.
 */



public class PrintActivity {


    // Setting for print
    private StarIOPort port = null;
    private String portName = "USB:";
    private String portSettings = "";
    private boolean sensorActiveHigh = true;
    private static int printableArea = 576;
    private boolean compressionEnable = true;
    private int source = com.brnokavarna.melounovycukr.app.R.drawable.logo_print_long2;
    private Context context;
    private Calendar c = Calendar.getInstance();

    private ArrayList<Polozka> listOfItems = new ArrayList<Polozka>();
    private int cenaCelkem;


    public PrintActivity(ArrayList<Polozka> listOfItems, int cenaCelkem, Context context) {

        this.listOfItems = listOfItems;
        this.cenaCelkem = cenaCelkem;
        this.context = context;

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


    // SEND COMMAND TO PRINTER
    private void sendCommand(Context context, String portName, String portSettings, ArrayList<Byte> byteList) {

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
        catch (StarIOPortException e)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setNegativeButton("Ok", null);
            AlertDialog alert = dialog.create();
            alert.setTitle("Failure");
            alert.setMessage(e.getMessage());
            alert.setCancelable(false);
            alert.show();
        }
        finally
        {
            if (port != null)
            {
                try
                {
                    StarIOPort.releasePort(port);
                }
                catch (StarIOPortException e) { }
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
    private String getWhiteSpaceBefore(Polozka element){

        String whiteSpaces = "";

        for(int i = element.getNazev().length(); i < 25; i++){

            whiteSpaces += " ";

        }

        return whiteSpaces;
    }


    // MAKE WHITE SPACES AFTER
    private String getWhiteSpaceAfter(Polozka element){

        String whiteSpaces = "";

        for(int i = (int) Math.log10(element.getMnozstvi()) + 1; i < 10; i++){

            whiteSpaces += " ";

        }

        return whiteSpaces;
    }



    //RASTER PRINT
    public void printRecipe(){


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
        String textToPrint = ("\nDatum: "+day+"."+month+"."+year+"            Čas: "+hour+":"+minute+"\r\n" +
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
        Iterator<Polozka> itr = listOfItems.iterator();
        while(itr.hasNext()) {
            Polozka element = itr.next();
            stringOfItems += (element.getNazev() + getWhiteSpaceBefore(element) + element.getMnozstvi() + getWhiteSpaceAfter(element) + element.getCena() + "\n");
        }

        commandContent = createRasterCommand(stringOfItems, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Final prize
        String prize = ("---------------------------------------\nCelkem                         " + cenaCelkem +" Kč");
        commandContent = createRasterCommand(prize, 12, 0);
        tempList = new Byte[commandContent.length];
        CopyArray(commandContent, tempList);
        list.addAll(Arrays.asList(tempList));

        // Last line
        String line = ("---------------------------------------\n       Děkujeme Vám za návštěvu.");
        commandContent = createRasterCommand(line, 12, 0);
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

