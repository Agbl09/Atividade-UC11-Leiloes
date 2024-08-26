

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        int status;
        
        try
        {
            prep= conn.prepareStatement("INSERT INTO produtos (`nome`, `valor`, `status`) "
                    + "VALUES(?,?,?)");
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3,produto.getStatus());
            
            status= prep.executeUpdate();
            
            return status;
            
        }
        
        catch(SQLException ex)
        {
            System.out.println("ERRO AO CONECTAR: "+ ex.getMessage());
            
            return ex.getErrorCode();
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException{
         
           ArrayList<ProdutosDTO> listaProdutos = new ArrayList<ProdutosDTO>();
       
           conn = new conectaDAO().connectDB();
           
        try{
           String sql= "SELECT * FROM produtos";
           
           PreparedStatement prep = conn.prepareStatement(sql);
           
           ResultSet rs = prep.executeQuery();
           
           while(rs.next())
           {
               ProdutosDTO produto = new ProdutosDTO();
               
               produto.setId(rs.getInt("id"));
               produto.setNome(rs.getString("nome"));
               produto.setValor(rs.getInt("valor"));
               produto.setStatus(rs.getString("status"));
               
               listaProdutos.add(produto);
           }
          
       }
       
       catch(SQLException e)
       {
           System.out.println("Erro ao listar os produtos");
       }
       
        return listaProdutos;
        
    }
    
    public int venderProduto(ProdutosDTO produto)
    {
        int status;
        
        conn = new conectaDAO().connectDB();
       
        
        try
        {
            
            prep = conn.prepareStatement("UPDATE produtos "
                    + "SET status= 'Vendido' WHERE id= ?");
            
            prep.setString(1, produto.getStatus());
            prep.setInt(2, produto.getId());
                        
            status= prep.executeUpdate();
            
            return status;
        
        }
        
        catch(SQLException ex)
        {
            System.out.println(ex.getErrorCode());
            
            return ex.getErrorCode();
        }
      
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws SQLException{
         
           ArrayList<ProdutosDTO> listaProdutosVendidos = new ArrayList<ProdutosDTO>();
       
           conn = new conectaDAO().connectDB();
           
        try{
           String sql= "SELECT * FROM produtos WHERE status= 'Vendido'";
           
           PreparedStatement prep = conn.prepareStatement(sql);
           
           ResultSet rs = prep.executeQuery();
           
           while(rs.next())
           {
               ProdutosDTO produto = new ProdutosDTO();
               
               produto.setId(rs.getInt("id"));
               produto.setNome(rs.getString("nome"));
               produto.setValor(rs.getInt("valor"));
               produto.setStatus(rs.getString("status"));
               
               listaProdutosVendidos.add(produto);
           }
          
       }
       
       catch(SQLException e)
       {
           System.out.println("Erro ao listar os produtos");
       }
       
        return listaProdutosVendidos;
        
    }
}

