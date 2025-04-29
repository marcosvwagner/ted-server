package engtelecom.std;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadServidor implements Runnable {

    private Socket cliente;

    public ThreadServidor(Socket cliente){
        this.cliente = cliente;
    }


    @Override
    public void run() {
        if (cliente != null) {
            System.out.println("Cliente conectado: " + cliente.getInetAddress());
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                OutputStreamWriter saida = new OutputStreamWriter(cliente.getOutputStream(), "UTF-8");

                while (true){

                    String mensagem = entrada.readLine();

                    System.out.println("ip: "+ cliente.getInetAddress() + "\nMensagem: " + mensagem);
                    if (mensagem.equals("fim")){
                        break;
                    }

                    saida.write(mensagem + "\n");
                    saida.flush();


                }


        } catch (Exception e) {
                System.out.printf("Erro: "+e.toString());
            }
        }
    }
}
