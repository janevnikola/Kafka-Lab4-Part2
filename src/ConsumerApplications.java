import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

class ConsumerApplications extends Thread {//ovie se consumers

    public void consume() throws InterruptedException, IOException {

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

                String koj_otklucil;
                String tip_korisnik;
                String prostorijaKojVlegol;
                String tip_prostorija;
                String[] nizaStudentLaboratorija = record.value().split(":");
                koj_otklucil = nizaStudentLaboratorija[0];
                tip_korisnik = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[1]));
                prostorijaKojVlegol = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[2]));
                tip_prostorija = String.valueOf(Integer.parseInt(nizaStudentLaboratorija[3]));

                if (tip_korisnik.equals("student")&&tip_prostorija.equals("kancelarija")) {
                    System.out.printf("Dobiv: Student vlegol vo kancelarija" +
                                    " Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                //    BufferedWriter writer = Files.newBufferedWriter(Paths.get("studenti.csv"));

                    // write header record
                    Long timestamp=record.timestamp();
        String complete=tip_korisnik+timestamp;

                        FileWriter outputfile = new FileWriter("C:\\Users\\user\\Downloads\\kafka\\profesori.csv");
                        BufferedWriter bufferWrt = new BufferedWriter(outputfile);
                        // create CSVWriter object filewriter object as parameter

                        bufferWrt.write(complete);
                    bufferWrt.close();

              }

            }

//check za koga i kade vlegol profesora
            for (ConsumerRecord<String, String> record : records_KogaIKadeVlegolProfesor) {

              //  if (record.key().equals("kancelarija")) {
                    String koj_otklucil;
                    String tip_korisnik;
                String prostorijaKojVlegol;
                String tip_prostorija;
                String[] nizaProfesor = record.value().split(":");

                koj_otklucil = nizaProfesor[0];
                tip_korisnik = String.valueOf(Integer.parseInt(nizaProfesor[1]));
                prostorijaKojVlegol = String.valueOf(Integer.parseInt(nizaProfesor[2]));
                tip_prostorija = String.valueOf(Integer.parseInt(nizaProfesor[3]));

                    if(tip_korisnik.equals("profesor")){
                        System.out.printf("Dobiv Profesor vlegol:  Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                                record.topic(), record.partition(),
                                record.offset(), record.key(), record.value());
                        Long timestamp=record.timestamp();
                        String complete=tip_korisnik+timestamp;

                        FileWriter outputfile = new FileWriter("C:\\Users\\user\\Downloads\\kafka\\profesori.csv");
                        BufferedWriter bufferWrt = new BufferedWriter(outputfile);
                        // create CSVWriter object filewriter object as parameter

                        bufferWrt.write(complete);
                        bufferWrt.close();

                    }

//                    Thread.sleep(izvrsuvanje);

                }


            //check za koga i kade vlegol student
            for (ConsumerRecord<String, String> record : records_KogaIKadeVlegolStudent) {

                String koj_otklucil;
                String tip_korisnik;
                String prostorijaKojVlegol;
                String tip_prostorija;
                String[] nizaStudent = record.value().split(":");
                koj_otklucil = nizaStudent[0];
                tip_korisnik = String.valueOf(Integer.parseInt(nizaStudent[1]));
                prostorijaKojVlegol = String.valueOf(Integer.parseInt(nizaStudent[2]));
                tip_prostorija = String.valueOf(Integer.parseInt(nizaStudent[3]));

                if(tip_korisnik.equals("student")) {
                    System.out.printf("Dobiv student vlegol: Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                }
            }
                }

            }

        }



