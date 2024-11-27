package lk.ijse.bistroculinaryacademyorm.dao.custom;


import lk.ijse.bistroculinaryacademyorm.dao.CrudDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Payment;

public interface PaymentDAO extends CrudDAO<Payment> {
    public Payment findPaymentById(String paymentId) throws Exception;
}
