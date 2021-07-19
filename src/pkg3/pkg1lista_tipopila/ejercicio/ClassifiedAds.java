
package pkg3.pkg1lista_tipopila.ejercicio;

import javax.swing.JOptionPane;

public class ClassifiedAds {

    public static void main(String[] args) {
       String days[]= {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
       String stripes[]={"Morning", "Afternoon", "Night"};
       String menu[]={"Add", "Print", "Print timezone", "Profits", 
             "Search client", "Delete", "Porcentage","Validate", "Exit"};
       String opt, client, message, resp;
       int code=1000, cod;  //code -> autoincremental, cod: variable para solicitar el codigo de un anuncio->para buscarlo
       double price;
       Advertsements ad;
       DoubleList TeleIUE = new DoubleList(); 
       boolean m[][];
       do{
           opt=(String)JOptionPane.showInputDialog(null, "Selected option", "Main menu",
                   1,null,menu, menu[0]);
           switch(opt)
           {
               case "Add":
                   m= new boolean[7][3];
                   client=JOptionPane.showInputDialog("Enter client");
                   message=JOptionPane.showInputDialog("Enter message");
                   price = Math.random()*500000;
                   //recorremos la matriz para llenarla
                   for (int i = 0; i < m.length; i++) {
                       for (int j = 0; j < m[0].length; j++) {
//                           do{
//                               resp = JOptionPane.showInputDialog("hire the strip " + days[i] +
//                                       "-" + stripes[j] + " (yes/no):");
//                           }while(!resp.equalsIgnoreCase("yes") && !resp.equalsIgnoreCase("no"));
                           double num = Math.random()*50;
                           if(num>40)
                               m[i][j]=true;
                           else
                               m[i][j]=false;
                       }                       
                   }
                   //creamos el objeto
                   ad = new Advertsements(code, client, price, message, m);
                   code++;
                   TeleIUE.AddLast(ad);
                   JOptionPane.showMessageDialog(null,"created advertsements");
                   break;
                   
               case "Print":
                   JOptionPane.showMessageDialog(null,TeleIUE.toString());
                   break;
                   
               case "Print timezone":
                   String day = (String)JOptionPane.showInputDialog(null, "Selected day",
                           "Days", 1, null, days, days[0]);
                   String stripe = (String)JOptionPane.showInputDialog(null, "Selected day",
                           "Days", 1, null, stripes, stripes[0]);
                   JOptionPane.showMessageDialog(null,TeleIUE.Search(SearchPos(days,day),
                           SearchPos(stripes,stripe)));
                   break;
                   
               case "Profits":
                          for (int i = 0; i < stripes.length; i++) {
                              JOptionPane.showMessageDialog(null, "the profits in " + stripes[i] + "are: " + 
                                      TeleIUE.Profits(i)+ "\n");                       
                            }
                          break;
                          
               case "Search client":
                   client=JOptionPane.showInputDialog(null, "Enter client");
                   JOptionPane.showMessageDialog(null,TeleIUE.SearchClient(client), client, 1);
                   break;
                   
               case  "Delete":
                   stripe = (String)JOptionPane.showInputDialog(null, "selected option",  "", 1, null, stripes, stripes[0]);
                   TeleIUE.Delete(SearchPos(stripes,stripe));
                   JOptionPane.showMessageDialog(null, "upadate information");
                   break;
                   
               case "Porcentage":
                   break;
               case "Validate":
                   TeleIUE.Validate();
                   break;
                          
           }
       }while(!opt.equals("Exit"));
    }
    
    public static int SearchPos(String v[], String value)
    {
        for (int i = 0; i < v.length; i++) {
            if(v[i].equals(value))
                return i;
        }
        return -1;
    }
}
