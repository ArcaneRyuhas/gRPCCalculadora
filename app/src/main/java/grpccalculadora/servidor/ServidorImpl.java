package grpccalculadora.servidor;

import calculadora.CalculadoraGrpc.CalculadoraImplBase;
import calculadora.CalculadoraOuterClass.DivRequest;
import calculadora.CalculadoraOuterClass.DivResponse;
import calculadora.CalculadoraOuterClass.MulRequest;
import calculadora.CalculadoraOuterClass.MulResponse;
import calculadora.CalculadoraOuterClass.ResRequest;
import calculadora.CalculadoraOuterClass.ResResponse;
import calculadora.CalculadoraOuterClass.SumRequest;
import calculadora.CalculadoraOuterClass.SumResponse;
import io.grpc.stub.StreamObserver;

public class ServidorImpl extends CalculadoraImplBase{


    @Override
    public void div(DivRequest request, StreamObserver<DivResponse> responseObserver) {
        DivResponse response = DivResponse
        .newBuilder().setResult(request.getA() / request.getB())
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void mul(MulRequest request, StreamObserver<MulResponse> responseObserver) {
        MulResponse response = MulResponse
        .newBuilder().setResult(request.getA() * request.getB())
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void res(ResRequest request, StreamObserver<ResResponse> responseObserver) {
        ResResponse response = ResResponse
        .newBuilder().setResult(request.getA() - request.getB())
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        SumResponse response = SumResponse
        .newBuilder().setResult(request.getA() + request.getB())
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    
    

}
