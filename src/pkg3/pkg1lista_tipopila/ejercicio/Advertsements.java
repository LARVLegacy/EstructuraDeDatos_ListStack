
package pkg3.pkg1lista_tipopila.ejercicio;


public class Advertsements {
    
    private int code;
    private String client;
    private double price;
    private String message;
    private boolean timezone[][];

    public Advertsements(int code, String client, double price, String message, boolean[][] timezone) {
        this.code = code;
        this.client = client;
        this.price = price;
        this.message = message;
        this.timezone = timezone;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean[][] getTimezone() {
        return timezone;
    }

    public void setTimezone(boolean[][] timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        //se debe recorrer la matriz para mostrarla en el toString
        String days[]= {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String stripes[]={"Morning", "Afternoon", "Night"};
        String mess="";
        for (int i = 0; i < timezone.length; i++) {
            for (int j = 0; j < timezone[0].length; j++) {
                if(timezone[i][j])
                    mess = mess + days[i]+"-"+stripes[j]+"\n";
            }            
        }
        
        return "Advertsements{" + "code=" + code + ", client=" + client + ", price=" + price + ", message=" + message 
                + ", timezone=" + mess + '}';
    }
    
    
    
}
