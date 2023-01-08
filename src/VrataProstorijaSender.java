import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Date;

class VrataProstorijaSender extends Thread{//ovie se producers
    private Informacii informacii;


    public VrataProstorijaSender() {
        informacii = new Informacii();
    }



   //TODO: При
    //TODO: отварањето на секоја врата се праќа информацијата кој отклучил, кој тип на корисник е
    //TODO: (професор, студент), во која просторија и каков тип на просторија (училница, лабораторија,
    //TODO: канцеларија).
    //TODO: (30) Направете клиентска апликација која ќе симулира врата од просторија и ќе праќа различни
    //TODO: логови со наведените информации.

    public void produce() {
        Thread one = new Thread() {
            public void run() {
                Integer partition = new Integer(0);
                Long timestamp = new Long(5);

                try {
                    Producer<String, String> producerStudent = new KafkaProducer<>(KafkaExample.createProps("student"));
                    Producer<String, String> producerProfesor = new KafkaProducer<>(KafkaExample.createProps("profesor"));

                    int i = 0;
                    while (true)     {
                        Date d = new Date();
    Informacii informacii_studentLab=new Informacii("Nikola","student",
            "138","laboratorija");

                        Informacii informacii_studentKancelarija=new Informacii("Nikola",
                                "student","138","kancelarija");

                        Informacii informacii_profesorLab=new Informacii("Boro","profesor",
                                "138","laboratorija");

                        Informacii informacii_profesorKancelarija=new Informacii("Sonja",
                                "profesor","12","kancelarija");

                        //(String topic, Integer partition, Long timestamp, K key, V value)

                        producerStudent.send(new ProducerRecord<String, String>(KafkaExample.topic,
                                partition, timestamp, "laboratorija", informacii_studentLab.toString()));
                        producerProfesor.send(new ProducerRecord<String, String>(KafkaExample.topic,
                                partition, timestamp, "kancelarija", informacii_studentKancelarija.toString()));

                        producerStudent.send(new ProducerRecord<String, String>(KafkaExample.topic,
                                partition, timestamp, "kancelarija", informacii_profesorLab.toString()));
                        producerProfesor.send(new ProducerRecord<String, String>(KafkaExample.topic,
                                partition, timestamp, "laboratorija", informacii_profesorKancelarija.toString()));
                        Thread.sleep(1000);
                        i++;

                    }


                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };
        one.start();
    }


}