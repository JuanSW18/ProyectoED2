/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cpm_interfaz.Respuesta;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Seven
 */
public class EventImprimir implements EventHandler<MouseEvent>{
    
    public String archivo = "D:\\Gantter.pdf";
    public TableView<Respuesta> aux_resp;
    public TextField filas;
    public TextField dur;

    public EventImprimir(TableView resp, TextField f, TextField duracion) {
        this.aux_resp = resp;
        this.filas = f;
        this.dur = duracion;
    }
    
    
    @Override
    public void handle(MouseEvent event) {
        crearPdf();
    }
    
    
    public void crearPdf() {
        try {
            int n = Integer.parseInt(filas.getText());
            ArrayList<Respuesta> tareas = new ArrayList();
            for(int i=0; i<n; i++){
                Respuesta p = new Respuesta();
                p.setNum_act(aux_resp.getItems().get(i).getNum_act());
                p.setActividad(aux_resp.getItems().get(i).getActividad());
                p.setTiempo_inicio(aux_resp.getItems().get(i).getTiempo_inicio());
                p.setTiempo_final(aux_resp.getItems().get(i).getTiempo_final());
                tareas.add(p);
            }
            Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
            PdfWriter escribir = null;

            escribir = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            //Agregamos un título al documento.
            //documento.addTitle("ARCHIVO PDF GENERADO DESDE JAVA");
            //Abrimos el documento a editar.
            documento.open();
            //Obtenemos la instancia de la imagen/logo.
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance("src\\Recursos\\homework.jpg");
            //Alineamos la imagen al centro del documento.
            imagen.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            //Agregamos la imagen al documento.
            documento.add(imagen);

            Paragraph vacio1 = new Paragraph();
            vacio1.add("\n\n");
            documento.add(vacio1);

            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setFont(FontFactory.getFont("Times New Roman", 24, Font.BOLD, BaseColor.BLACK));
            titulo.add("***ASIGNACION DE TAREAS***");
            documento.add(titulo);
            Paragraph saltolinea1 = new Paragraph();
            saltolinea1.add("\n\n");
            documento.add(saltolinea1);
            
            PdfPTable tabla = new PdfPTable(4);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{30, 30, 30, 30});

            Paragraph col1 = new Paragraph("N° de Actividades", FontFactory.getFont("arial", 11, BaseColor.WHITE));
            col1.getFont().setStyle(Font.BOLD);
            col1.getFont().setSize(10);
            
            PdfPCell celda1 = new PdfPCell(col1);
            celda1.setBackgroundColor(BaseColor.BLACK);
            celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda1);

            Paragraph col2 = new Paragraph("Nombre de Actividad", FontFactory.getFont("arial", 11, BaseColor.WHITE));
            col2.getFont().setStyle(Font.BOLD);
            col2.getFont().setSize(10);

            PdfPCell celda2 = new PdfPCell(col2);
            celda2.setBackgroundColor(BaseColor.BLACK);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda2);

            Paragraph col3 = new Paragraph("Tiempo de inicio", FontFactory.getFont("arial", 11, BaseColor.WHITE));
            col3.getFont().setStyle(Font.BOLD);
            col3.getFont().setSize(10);

            PdfPCell celda3 = new PdfPCell(col3);
            celda3.setBackgroundColor(BaseColor.BLACK);
            celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda3);

            Paragraph col4 = new Paragraph("Tiempo final", FontFactory.getFont("arial", 11, BaseColor.WHITE));
            col4.getFont().setStyle(Font.BOLD);
            col4.getFont().setSize(10);

            PdfPCell celda4 = new PdfPCell(col4);
            celda4.setBackgroundColor(BaseColor.BLACK);
            celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda4);
            //agregar los datos a la tabla
            for (int i = 0; i < tareas.size(); i++) {
                tabla.addCell(tareas.get(i).getNum_act());
                tabla.addCell(tareas.get(i).getActividad());
                tabla.addCell(tareas.get(i).getTiempo_inicio());
                tabla.addCell(tareas.get(i).getTiempo_final());
            }
            //Añadimos la tabla "tabla" al documento "documento".
            documento.add(tabla);
            Paragraph duracion = new Paragraph();
            duracion.setAlignment(Paragraph.ALIGN_LEFT);
            duracion.setFont(FontFactory.getFont("Times New Roman", 12, Font.BOLD, BaseColor.BLACK));
            duracion.add("Tiempo de duracion: " + this.dur.getText());
            documento.add(duracion);
            //Cerramos el documento.
            documento.close();
            //Cerramos escribir.
            escribir.close();
        } catch (DocumentException | IOException ex) {
            System.out.println("ErrorGeneral");
        }
        try {
            File path = new File(archivo);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            System.out.println("Error al abrir pdf");
        }
    }
    
}
