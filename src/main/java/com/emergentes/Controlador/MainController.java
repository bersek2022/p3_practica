
package com.emergentes.controlador;

import com.emergentes.modelo.producto;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal=new ConexionDB();
        Connection conn =canal.conectar();
        ResultSet rs;
        String op;
        ArrayList <producto> lista=new ArrayList<>();
        
        int id;
       
        op=(request.getParameter("op")!=null)?request.getParameter("op"):"list";
        
       if (op.equals("list")) {
             //operaciones para listar datos
               String sql="select * from productos";
           try {
               //consulata de selecccion y alamacenamiento
               ps=conn.prepareStatement(sql);
               rs=ps.executeQuery();
                 while(rs.next()){
                      
                   producto pro=new producto();
                  
                   pro.setId(rs.getInt("id"));
                   pro.setProducto(rs.getString("producto"));
                   pro.setPrecio(rs.getFloat("precio"));
                   pro.setCantidad(rs.getInt("cantidad"));
                   
                   lista.add(pro);
             }
            
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("index.jsp").forward(request, response);
          } catch (SQLException ex) {
              Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
          }
            
          }
         if (op.equals("nuevo")) {
              producto pr = new producto();
              //System.out.println(li.toString());
              request.setAttribute("pro", pr);
              request.getRequestDispatcher("editar.jsp").forward(request, response);
          }
          
        if (op.equals("editar")) {
           id=Integer.parseInt(request.getParameter("id"));
           try{
               producto pro1= new producto();
               ps=conn.prepareStatement("select * from productos where id=?");
               ps.setInt(1, id);
               rs= ps.executeQuery();
               if (rs.next()) {
                pro1.setId(rs.getInt("id"));
                pro1.setProducto(rs.getString("producto"));
                pro1.setPrecio(rs.getFloat("precio"));
                pro1.setCantidad(rs.getInt("cantidad"));
               }
               request.setAttribute("pro", pro1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               
           }catch(SQLException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
           }
          }
       
        if (op.equals("eliminar")) {
             id=Integer.parseInt(request.getParameter("id"));
          try {
              ps=conn.prepareStatement("delete from productos where id=?");
              ps.setInt(1,id);
              
              ps.executeUpdate();
              response.sendRedirect("MainController");
             } catch (SQLException ex) {
              Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
            }
      }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            /* int id = Integer.parseInt(request.getParameter("id")==null || 
                request.getParameter("id").trim().equals("")?"0":request.getParameter("id"));*/
        int id=Integer.parseInt(request.getParameter("id"));
            //System.out.println("Valor de ID"+id);
       
        String producto =request.getParameter("producto");
        Float precio = Float.parseFloat(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            producto pro = new producto();
            pro.setId(id);
            pro.setProducto(producto);
            pro.setPrecio(precio);
            pro.setCantidad(cantidad);
       
            ConexionDB canal=new ConexionDB();
            Connection conn =canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
        
        if (id==0) {
                ///isertar registro
            String sql="insert into productos(producto,precio,cantidad)values(?,?,?)" ;  
            try {
                ps=conn.prepareStatement(sql);
                ps.setString(1,pro.getProducto());
                ps.setFloat(2,pro.getPrecio());
                ps.setInt(3,pro.getCantidad());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            }else{
              //actualizar de registro .............................
            String sql="update productos set producto=?,precio=?,cantidad=? where id=?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, pro.getProducto());
                ps.setFloat(2, pro.getPrecio());
                ps.setInt(3, pro.getCantidad());
                ps.setInt(4, pro.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                response.sendRedirect("MainController");
       
         }

}
