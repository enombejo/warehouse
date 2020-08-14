package org.example.warehouse.service;


import jdk.nashorn.internal.ir.IfNode;
import org.example.warehouse.models.*;
import org.example.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.nio.file.attribute.GroupPrincipal;
import java.util.*;

@Service
/**
 * Класс сервис для работы с операциями приема товаров по накладным, списания, покупки
 */
public class TransactionService {
    public TransactionService() {
    }

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private WaybillRepository waybillRepositoryDB;

    @Autowired
    private WriteOffRepository writeOffRepository;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    /**
     * Прием товара по накладным
     */
    public boolean goodsReception(WaybillService waybillService) {
        List<Goods> goods = goodsRepository.findByName(waybillService.getProduct());
        Optional<Group> group = groupRepository.findByName(waybillService.getGroupProduct());
//        boolean isPresentGroup = false;
//        if (group.isPresent()) {
//            //если группа товара соответствует и не совпадают цена или описание товара и если не пустой
//            isPresentGroup = (group.get().getName().equals(waybillService.getGroupProduct()));
//        }
        Goods newGoods = new Goods();
        newGoods.setPrice(waybillService.getPrice());
        newGoods.setName(waybillService.getProduct());
        newGoods.setQuantity(waybillService.getAmount());
        newGoods.setGroup(new Group(waybillService.getGroupProduct()));
        
        Waybill waybill = new Waybill();
        waybill.setPrice(waybillService.getPrice());
        waybill.setAmount(waybillService.getAmount());
        waybill.setProduct(waybillService.getProduct());
        waybill.setSupplier(waybillService.getSupplier());
        waybill.setWaybillDate(waybillService.getWaybillDate());
        waybill.setGroupProduct(waybillService.getGroupProduct());


        boolean parametersGoods;
        boolean parametersGroup;
        //
        if (group.isPresent() && !goods.isEmpty()) {
            for (Goods goods1 : goods) {
                System.out.println("!");
                System.out.println("!!");
                //Сравнение параметров товара в бд с параметрами товара в накладной (описание  товара, цена)
                parametersGoods = (goods1.getPrice().doubleValue() == waybillService.getPrice()) && goods1.getName().equals(waybillService.getProduct());
                //Группа товара в накладно и группа в бд соответствует или нет
                parametersGroup = (goods1.getGroup().getName().equals(waybillService.getGroupProduct()));

                //Если такая группы продуктов уже существует и есть продукт с таким описанием и ценой, то увеличиваем количество и сохраняем
                if (parametersGroup && parametersGoods) {
                    long amountSum = goods1.getQuantity() + waybillService.getAmount();
                    goods1.setQuantity(amountSum);
                    waybill.setGoodsId(goods1);
                    Set<Waybill> waybillSet = goods1.getWaybills();
                    waybillSet.add(waybill);
                    goods1.setWaybills(waybillSet);
                    System.out.println("I");
                    goodsRepository.save(goods1);
                    return true;
                //Если группа соответсквует, но не соответствует цена или наименование товара, то добавляем новый товар в группу
                } else if (parametersGroup && (!(goods1.getPrice().doubleValue() == waybillService.getPrice()) || !goods1.getName().equals(waybillService.getProduct()))) {
                    System.out.println("IV");
                    Set<Goods> goodsSet = group.get().getGoods();
                    goodsSet.add(newGoods);
                    group.get().setGoods(goodsSet);
                    newGoods.setGroup(group.get());
                    waybill.setGoodsId(newGoods);
                    waybillRepositoryDB.save(waybill);
                    //goodsRepository.save(newGoods);
                    //groupRepository.save(group.get());
                    return true;
                }
            }
        } else if (group.isPresent() && goods.isEmpty()) {
            System.out.println("II");
            Set<Goods> goodsSet = group.get().getGoods();
            goodsSet.add(newGoods);
            group.get().setGoods(goodsSet);
            newGoods.setGroup(group.get());
            waybill.setGoodsId(newGoods);
            waybillRepositoryDB.save(waybill);
            //goodsRepository.save(newGoods);
            //groupRepository.save(group.get());
            return true;
        } else {
            System.out.println("III");
            //Goods newGoods = new Goods();
            //Group group = new Group(waybillService.getGroupProduct());
            //Set<Goods> goodsSet = group.getGoods();
            //goodsSet.add(newGoods);
            //newGoods.setGroup(group);
            waybill.setGoodsId(newGoods);
            waybillRepositoryDB.save(waybill);
//            goodsRepository.save(newGoods);
            return true;
        }


//        if (!goods.isEmpty()) {
//            for (Goods goods1 : goods) {
//
//            }
//            System.out.println("!");
//            for (Goods goods1 : goods) {
//                System.out.println("!!");
//                //Сравнение параметров товара в бд с параметрами товара в накладной (описание  товара, цена)
//                parametersGoods = (goods1.getPrice().doubleValue() == waybillService.getPrice()) && goods1.getName().equals(waybillService.getProduct());
//                //Группа товара в накладно и группа в бд соответствует или нет
//                parametersGroup = (goods1.getGroup().getName().equals(waybillService.getGroupProduct()));
//
//                //Если такая группы продуктов уже существует и есть продукт с таким описанием и ценой, то увеличиваем количество и сохраняем
//                if (parametersGroup && parametersGoods) {
//                    long amountSum = goods1.getQuantity() + waybillService.getAmount();
//                    goods1.setQuantity(amountSum);
//                    waybill.setGoodsId(goods1);
//                    Set<Waybill> waybillSet = goods1.getWaybills();
//                    waybillSet.add(waybill);
//                    goods1.setWaybills(waybillSet);
//                    System.out.println("I");
//                    goodsRepository.save(goods1);
//                    return true;
//
//                }
//            }
//        //Иначе если группа товара соответствует и не совпадают цена или описание товара, то создаем новый товар в бд с той же группой
//        } else if (isPresentGroup) {
//
//            System.out.println("II");
//            Set<Goods> goodsSet = group.get().getGoods();
//            goodsSet.add(newGoods);
//            group.get().setGoods(goodsSet);
//            newGoods.setGroup(group.get());
//            waybill.setGoodsId(newGoods);
//            waybillRepositoryDB.save(waybill);
//            //goodsRepository.save(newGoods);
//            //groupRepository.save(group.get());
//            return true;
//            //Если группа товара другая, то сохраняем новый товар под новой группой
//        //Иначе если нет продугта и шруппы, то создаем новый
//        } else {
//            System.out.println("IV");
//            //Goods newGoods = new Goods();
//            //Group group = new Group(waybillService.getGroupProduct());
//            //Set<Goods> goodsSet = group.getGoods();
//            //goodsSet.add(newGoods);
//            //newGoods.setGroup(group);
//            waybill.setGoodsId(newGoods);
//            waybillRepositoryDB.save(waybill);
////            goodsRepository.save(newGoods);
//            return true;
//
//        }



        
        
//        if (goods.isPresent()/* || group.isPresent()*/) {
//            //Сравнение параметров товара в бд с параметрами товара в накладной (описание  товара, цена)
//            System.out.print(goods.get().getName().equals(waybillService.getProduct()) + " equals(waybillService.getProduct()  ");
//            System.out.println((goods.get().getPrice().doubleValue() == waybillService.getPrice()) + " goods.get().getPrice() == waybillService.getPrice()");
//            boolean parametersGoods = (goods.get().getName().equals(waybillService.getProduct())) && (goods.get().getPrice().doubleValue() == waybillService.getPrice());
//            System.out.println(parametersGoods + " parametersGoods");
//            //Группа товара в накладно и группа в бд соответствует или нет
//            boolean parametersGroup = (goods.get().getGroup().getName().equals(waybillService.getGroupProduct()));
//            System.out.println(parametersGroup + " parametersGroup");
//            //Если такая группы продуктов уже существует и есть продукт с таким описанием и ценой, то увеличиваем количество и сохраняем
//            if (parametersGroup && parametersGoods) {
//                long amountSum = goods.get().getQuantity() + waybillService.getAmount();
//                goods.get().setQuantity(amountSum);
//
//                waybill.setGoodsId(goods.get());
//                Set<Waybill> waybillSet = goods.get().getWaybills();
//                waybillSet.add(waybill);
//                goods.get().setWaybills(waybillSet);
//                System.out.println("!");
//                //newGoods = goods.get();
//                goodsRepository.save(goods.get());
//                return true;
//            //Иначе если группа товара соответствует и не совпадают цена или описание товара, то создаем новый товар в бд с той же группой
//            } else if (parametersGroup && !parametersGoods) {
//                //Goods newGoods = new Goods();
//                System.out.println("II");
//                Set<Goods> goodsSet = group.get().getGoods();
//                goodsSet.add(newGoods);
//                group.get().setGoods(goodsSet);
//                newGoods.setGroup(group.get());
//                waybill.setGoodsId(newGoods);
//                waybillRepositoryDB.save(waybill);
//                //goodsRepository.save(newGoods);
//                //groupRepository.save(group.get());
//                return true;
//            //Если группа товара другая, то сохраняем новый товар под новой группой
//            } else if (!parametersGroup) {
//                //Goods newGoods = new Goods();
//                
//                //goodsRepository.save(newGoods);
//            }
//        //Иначе если нет продугта и шруппы, то создаем новый
//        } else {
//            //Goods newGoods = new Goods();
//            //Group group = new Group(waybillService.getGroupProduct());
//            //Set<Goods> goodsSet = group.getGoods();
//            //goodsSet.add(newGoods);
//            //newGoods.setGroup(group);
//            waybill.setGoodsId(newGoods);
////            goodsRepository.save(newGoods);
//
//        }
//        //Сохраняю накладную в БД
//
////        waybill.setPrice(waybillService.getPrice());
////        waybill.setAmount(waybillService.getAmount());
////        waybill.setProduct(waybillService.getProduct());
////        waybill.setSupplier(waybillService.getSupplier());
////        waybill.setWaybillDate(waybillService.getWaybillDate());
//        //waybill.setGroupProduct(waybillService.getGroupProduct());
//        //waybill.setGoodsId(newGoods);
//        waybillRepositoryDB.save(waybill);
        //goodsRepository.save(newGoods);
        return false;
    }


    /**
     * Метод работает с заявкой на списание товара
     * @param writeOffService прием заявок
     * @return возвращает true, если операция прошла успешно, иначе false
     */
    public boolean writeOffApplication(WriteOffService writeOffService) {
        Optional<Goods> goods = goodsRepository.findById(writeOffService.getIdProduct());
        WriteOff writeOffNew = new WriteOff();
        writeOffNew.setAmount((long)writeOffService.getAmount());
        writeOffNew.setApplicationDate(writeOffService.getDate());
        writeOffNew.setPersonInCharge(writeOffService.getFullName());
        writeOffNew.setCause(writeOffService.getCause());
        Confirmation confirmationNew = new Confirmation();

        //Если есть такой товар
        if (goods.isPresent()) {
            List<WriteOff> writeOff = writeOffRepository.findByGoods(goods.get());
            writeOffNew.setProduct(goods.get().getName());
            writeOffNew.setGroupProduct(goods.get().getGroup().getName());
            writeOffNew.setGoods(goods.get());
            goods.get().setStatus("write_off");
            int amountWriteOff = writeOffService.getAmount();

            //Если есть по этим товарам не подтвержденные заявки на списание
            if (!writeOff.isEmpty()) {

                System.out.println("I");

                for (WriteOff writeOff1: writeOff) {
                    if ((writeOff1.getConfirmation() == null)) {
                        amountWriteOff += writeOff1.getAmount();
                    }
                }
                if ((goods.get().getQuantity() - amountWriteOff) < 0) {
                    return false;
                } else {
                    //!!! надо протестить еще!!!
                    //Сохраняем и меняем статус
                    goodsRepository.save(goods.get());
                    //confirmationNew.setWriteOff();
                    //writeOffNew.setConfirmation(confirmationNew);
                    writeOffRepository.save(writeOffNew);
                }
            }
            else {
                if ((goods.get().getQuantity() - amountWriteOff) < 0) {
                    return false;
                } else {
                    //Сохраняем и меняем статус
                    goodsRepository.save(goods.get());
                    //writeOffNew.setConfirmation(confirmationNew);
                    writeOffRepository.save(writeOffNew);
                }
            }
        }

        return false;
    }



    public boolean writeOffConfirmation(ConfirmationService confirmationService) {
        Optional<WriteOff> writeOff = writeOffRepository.findById(confirmationService.getNumber());

        Confirmation confirmationNew = new Confirmation();
        confirmationNew.setApplicant(confirmationService.getApplicant());
        confirmationNew.setYesNo(confirmationService.getYesNo());
        confirmationNew.setConfirmationDate(confirmationService.getConfirmationDate());
        //confirmationNew.set
        System.out.println("I");

        if (writeOff.isPresent()) {
            Optional<Goods> goods = goodsRepository.findById(writeOff.get().getGoods().getId());
            System.out.println("II");

            if (writeOff.get().getConfirmation() == null) {
                System.out.println("III");
                int confirmationCount = 0;
                for (WriteOff writeOff1: writeOff.get().getGoods().getWriteOffs()) {
                    if (writeOff1.getConfirmation() == null) {
                        confirmationCount++;
                    }
                }
                if (!(confirmationCount > 1)) {
                    writeOff.get().getGoods().setStatus("");
                }
                if (confirmationService.getYesNo()) {
                    System.out.println("IV");
                    long newAmount = writeOff.get().getGoods().getQuantity() - writeOff.get().getAmount();
                    System.out.println(newAmount);
                    if (newAmount > 0) {
                        System.out.println("V");

                        //writeOff.get().setConfirmation(confirmationNew);
                        writeOff.get().getGoods().setQuantity(newAmount);
                        confirmationNew.setWriteOff(writeOff.get());
                        confirmationRepository.save(confirmationNew);
                        //writeOffRepository.save(writeOff.get());
                        return true;
                    } else if (newAmount == 0) {        //!!!
                        System.out.println("VIII");
                        //writeOff.get().setGoods(null);
                        ;

                        if (goods.isPresent()) {
//                            Set<Goods> goodsSet = goods.get().getGroup().getGoods();
//                            for (Goods goods1: goodsSet) {
//                                if (goods1.equals(goods.get())) {
//                                    goodsSet.remove(goods1);
//                                    goods.get().getGroup().setGoods(goodsSet);
//                                    groupRepository.save(goods.get().getGroup());
//                                    break;
//                                }
//                            }
                            Set<WriteOff> writeOffSet = goods.get().getWriteOffs();
                            Set<WriteOff> newWriteOffSet = new HashSet();
                            int tmp=0;
                            for (WriteOff writeOff1 : writeOffSet) {
                                if (writeOff1.getGoods() != null) {
                                    writeOff1.setGoods(null);
                                    newWriteOffSet.add(writeOff1);
                                    //writeOffSet.remove(writeOff1);
                                    //writeOffSet.add(writeOff1);
                                    //writeOff.get().setGoods(null);
                                    //writeOffSet.clear();
                                    //break;
                                    //tmp++;

                                }
                                //writeOff1.setGoods(null);
                                //writeOffSet.remove(writeOff1);
                                //writeOffSet.add(writeOff1);
                            }
                            goods.get().setWriteOffs(newWriteOffSet);
                            writeOff.get().setGoods(goods.get());
                            writeOffRepository.save(writeOff.get());
                            confirmationNew.setWriteOff(writeOff.get());
                            System.out.println("delete");
                            confirmationRepository.save(confirmationNew);
                            goodsRepository.delete(goods.get());

                        }


                    }
                } else {
                    System.out.println("VI");
                    //writeOff.get().setConfirmation(confirmationNew);
                    confirmationNew.setWriteOff(writeOff.get());
                    confirmationRepository.save(confirmationNew);
                    //writeOffRepository.save(writeOff.get());
                    return true;
                }
            }
        }
        System.out.println("VII");
        return false;
    }

}


