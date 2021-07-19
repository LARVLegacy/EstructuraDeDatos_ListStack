
package pkg3.pkg1lista_tipopila.ejercicio;

public class DoubleList {
    
    private DoubleNode first;
    private DoubleNode last;

    public DoubleList() {
    }
    
    public boolean isEmpty()
    {
        return first==null && last == null;
    }
    
    public void AddFirst(Object data)
    {
        if(isEmpty())
        {
            first = new DoubleNode(data);
            last = first;
        }
        else
        {
            DoubleNode n = new DoubleNode(data);
            first.setPrevious(n);
            n.setNext(first);
            first = n;
        }
    }
    
    @Override
    public String toString()
    {
        String text="";
        DoubleNode aux = first;
        while(aux != null)
        {
            text = text + aux.getData() + "\n";
            aux = aux.getNext();
        }
        return text;
    }
    
    public void AddLast(Object data)
    {
        if(isEmpty())
            AddFirst(data);
        else
        {
            DoubleNode n = new DoubleNode(data);
            last.setNext(n);
            n.setPrevious(last);
            last=n;
        }
    }
    
    public int Size()
    {
        int counter = 0;
        DoubleNode aux = last;  //DoubleNode aux = first
        while(aux!=null)
        {
            counter++;
            aux = aux.getPrevious(); //aux = aux.getNext();
        }
        return counter;
    }

    //insertar en una posición determinada, garantizando que posicion sea valida
    public void Add(Object data, int pos)
    {
        if(pos == 1)
            AddFirst(data);
        else
        {
            if(pos == Size()+1)
                AddLast(data);
            else
            {
                DoubleNode aux = first;
                int counter=1;
                //recorremos buscando la posicion en la que vamos a insertar
                while(aux!=null && counter<pos)
                {
                    aux = aux.getNext();
                    counter++;
                }
                DoubleNode n = new DoubleNode(data);
                n.setNext(aux);
                n.setPrevious(aux.getPrevious());
                aux.getPrevious().setNext(n);
                aux.setPrevious(n);
            }
        }
    }
    
    public void Add(Object data)
    {
        DoubleNode aux = first;
        while(aux!=null  && ((String)data).compareTo((String)aux.getData())>0)
            aux = aux.getNext();
        
        if(aux!=null)
        {
            if(aux.getPrevious()!=null)
            {
                DoubleNode n = new DoubleNode(data);
                n.setNext(aux);
                n.setPrevious(aux.getPrevious());
                aux.getPrevious().setNext(n);
                aux.setPrevious(n);
            }
            else
                AddFirst(data);
        }
        else
            AddLast(data);
    }
    
    public boolean RemoveFirst()
    {
        if(!isEmpty())
        {
            first = first.getNext();
            if(first == null)
                last = null;
            else
                first.setPrevious(null);
            
            return true;
        }
        return false;
    }
    
    public boolean RemoveLast()
    {
        if(!isEmpty())
        {
            last = last.getPrevious();
            if(last == null)
                first = null;
            else
                last.setNext(null);
            
            return true;
        }
        return false;
    }

    public boolean Remove(int pos)
    {
        if(!isEmpty())
        {
            if(pos==1)
                RemoveFirst();
            else
            {
                if(pos==Size())
                    RemoveLast();
                else
                {
                    int cont=1;
                    DoubleNode aux = first;
                    while(cont<pos){
                        aux=aux.getNext();
                        cont++;
                    }
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                }
            }
            return true;
        }
        
        return false;
    }
    
    public boolean Remove(String name)
    {
        DoubleNode aux = first;
        while(aux!=null && !((String)aux.getData()).equalsIgnoreCase(name))
            aux=aux.getNext();
        
        if(aux!=null)  //se encontró el elemento
        {
            if(aux.getPrevious()==null) //preguntamos si es el primero
                RemoveFirst();
            else
            {
                if(aux.getNext()==null) //preguntamos si es el ultimo
                    RemoveLast();
                else
                {
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                }
            }
            return true;
        }
        return false;
    }
    
    /*************************/
    public String Search (int day, int stripe)
    {
        String mess="";
        DoubleNode aux = first;
        boolean m[][];
        while(aux!=null)
        {
            m=((Advertsements)aux.getData()).getTimezone();  //
            if(m[day][stripe])
                mess = mess + aux.getData().toString() + "\n";
            
            aux = aux.getNext();
        }
        
        return mess;
    }
    
    public double Profits(int stripe)
    {
        double total=0;
        DoubleNode aux=first;
        boolean m[][];
        while(aux!=null)
        {
            m = ((Advertsements)(aux.getData())).getTimezone();
            for (int i = 0; i < m.length; i++) {               
                if(m[i][stripe])
                    total += ((Advertsements)aux.getData()).getPrice();
            }
            aux=aux.getNext();
        }
        return total;
    }
    
    public DoubleList SearchClient (String client)
    {
        DoubleList list = new DoubleList();
        DoubleNode aux = last;  //first
        while(aux!=null)
        {
            if(((Advertsements)aux.getData()).getClient().equalsIgnoreCase(client))
                list.AddLast(aux.getData());
            
            aux=aux.getPrevious();  //aux = aux.getNext();
        }
        return list;
    }
    
    public void Delete(int stripe)
    {
        boolean m[][];
        DoubleNode aux = first;
        while(aux!=null)
        {
            m=((Advertsements)aux.getData()).getTimezone();
            for (int i = 0; i < m.length; i++) {
                m[i][stripe]=false;
                
            }
            aux = aux.getNext();
        }
        Validate();
    }
    
    public void Validate()
    {
        int pos=Size();
        DoubleNode aux = last;
        boolean m[][];
        boolean res;  //la matriz tiene no datos
        while(aux!=null)
        {
            res=false;
            m=((Advertsements)aux.getData()).getTimezone();
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    if(m[i][j])
                        res=true;  //existe al menos un dato
                }                
            }
            if(!res)
                Remove(pos);
            
            pos--;
            aux=aux.getPrevious();                    
        }
    }
}
