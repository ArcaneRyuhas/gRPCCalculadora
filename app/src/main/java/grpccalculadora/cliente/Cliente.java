package grpccalculadora.cliente;

import javax.swing.JOptionPane;
import calculadora.CalculadoraOuterClass.DivRequest;
import calculadora.CalculadoraOuterClass.DivResponse;
import calculadora.CalculadoraOuterClass.MulRequest;
import calculadora.CalculadoraOuterClass.MulResponse;
import calculadora.CalculadoraOuterClass.ResRequest;
import calculadora.CalculadoraOuterClass.ResResponse;
import calculadora.CalculadoraOuterClass.SumRequest;
import calculadora.CalculadoraOuterClass.SumResponse;
import calculadora.CalculadoraGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {

    public static void main (String [] args){
        String host = "localhost";
        int puerto = 8080;

        ManagedChannel ch = ManagedChannelBuilder.forAddress(host, puerto).usePlaintext().build();
        CalculadoraGrpc.CalculadoraBlockingStub stub = CalculadoraGrpc.newBlockingStub(ch);

        while (true) { 
            String opt = JOptionPane.showInputDialog(
                "Calcular\n" +
                    "Suma......1\n" + 
                    "Resta.....2\n" + 
                    "Multip....3\n" +
                    "Division..4\n" + 
                    "Cancelar para salir");
            
            if (opt == null) {
                break;
            }

            int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
            int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

            switch (opt) {
                case "1":
                    SumRequest sumRequest = SumRequest.newBuilder().setA(a).setB(b).build();
                    SumResponse sumResponse = stub.sum(sumRequest);
                    JOptionPane.showMessageDialog(null, a + "+" + b + "=" + sumResponse.getResult());
                    break;
            
                case "2":
                    ResRequest resRequest = ResRequest.newBuilder().setA(a).setB(b).build();
                    ResResponse resResponse = stub.res(resRequest);
                    JOptionPane.showMessageDialog(null, a + "-" + b + "=" + resResponse.getResult());
                    break;

                case "3":
                    MulRequest mulRequest = MulRequest.newBuilder().setA(a).setB(b).build();
                    MulResponse mulResponse = stub.mul(mulRequest);
                    JOptionPane.showMessageDialog(null, a + "*" + b + "=" + mulResponse.getResult() );
                    break;

                case "4":
                    DivRequest divRequest = DivRequest.newBuilder().setA(a).setB(b).build();
                    DivResponse divResponse = stub.div(divRequest);
                    JOptionPane.showMessageDialog(null, a + "/" + b + "=" + divResponse.getResult() );
                    break;
            }
        }   
    }
}
