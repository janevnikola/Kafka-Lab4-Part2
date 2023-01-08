import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;

class ConsumerApplications extends Thread {//ovie se consumers

    public void consume() throws InterruptedException {

        //brokers se bootstrap servers
        //group e consumer group id

        //Create a consumer
        KafkaConsumer<String, String> consumerDaliStudentOtvorilKancelarija = new KafkaConsumer<>(KafkaExample.createProps("student"));

       KafkaConsumer<String, String> consumerKogaIKadeVlegolProfesor = new KafkaConsumer<>(KafkaExample.createProps("profesor"));
        KafkaConsumer<String, String> consumerKogaIKadeVlegolStudent = new KafkaConsumer<>(KafkaExample.createProps("student"));

        //subscribe na topic
        consumerDaliStudentOtvorilKancelarija.subscribe(Collections.singletonList(KafkaExample.topic));
        consumerKogaIKadeVlegolProfesor.subscribe(Collections.singletonList(KafkaExample.topic));
        consumerKogaIKadeVlegolStudent.subscribe(Collections.singletonList(KafkaExample.topic));

        while (true) {
            ConsumerRecords<String, String> records_DaliStudentOtvorilKancelarija = consumerDaliStudentOtvorilKancelarija.poll(1000);
            ConsumerRecords<String, String> records_KogaIKadeVlegolProfesor = consumerKogaIKadeVlegolProfesor.poll(1000);
            ConsumerRecords<String, String> records_KogaIKadeVlegolStudent = consumerKogaIKadeVlegolStudent.poll(1000);




            //dali otvoril student kancelarija
            for (ConsumerRecord<String, String> record : records_DaliStudentOtvorilKancelarija) {
                //  Thread.sleep(izvrusvanje);
//                if (record.key().equals("laboratorija")) {
                    System.out.printf("Primiv Laboratorija Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                    //poraka=record.key();
                   // String tip;//nemame potreba od proveruvanje bidejki vo ifot imame

                     String koj_otklucil;
                     String tip_korisnik;
                     String prostorijaKojVlegol;
                     String tip_prostorija;
                    String[] nizaStudentLaboratorija = record.value().split(":");
                    koj_otklucil = nizaStudentLaboratorija[0];
                    tip_korisnik = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[1]));
                    prostorijaKojVlegol = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[2]));
                    tip_prostorija = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[3]));



  //              }

            }

//check za koga i kade vlegol profesor
            for (ConsumerRecord<String, String> record : records_KogaIKadeVlegolProfesor) {

        //        if (record.key().equals("kancelarija")) {

                    System.out.printf("Dobiv kancelarija Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                    String koj_otklucil;
                    String tip_korisnik;
                    String prostorijaKojVlegol;
                    String tip_prostorija;
                    String[] nizaStudentLaboratorija = record.value().split(":");
                    koj_otklucil = nizaStudentLaboratorija[0];
                    tip_korisnik = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[1]));
                    prostorijaKojVlegol = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[2]));
                    tip_prostorija = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[3]));



//                    Thread.sleep(izvrsuvanje);

                //}

            }
            //check za koga i kade vlegol student
           /* for (ConsumerRecord<String, String> record : records_KogaIKadeVlegolStudent) {

                if (record.key().equals("kancelarija")) {

                    System.out.printf("Dobiv Compute Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                    String koj_otklucil;
                    String tip_korisnik;
                    String prostorijaKojVlegol;
                    String tip_prostorija;
                    String[] nizaStudentLaboratorija = record.value().split(":");
                    koj_otklucil = nizaStudentLaboratorija[0];
                    tip_korisnik = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[1]));
                    prostorijaKojVlegol = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[2]));
                    tip_prostorija = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[3]));


                }

            }*/

        }
    }

}