package lk.ijse.bistroculinaryacademyorm.bo.custom.impl;



import lk.ijse.bistroculinaryacademyorm.bo.custom.PaymentBo;
import lk.ijse.bistroculinaryacademyorm.dao.DAOFactory;
import lk.ijse.bistroculinaryacademyorm.dao.custom.EnrollmentDAO;
import lk.ijse.bistroculinaryacademyorm.dao.custom.PaymentDAO;
import lk.ijse.bistroculinaryacademyorm.dto.PaymentDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Enrollment;
import lk.ijse.bistroculinaryacademyorm.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Payment);
    EnrollmentDAO enrollmentDAO = (EnrollmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Enrollment);
    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        Enrollment enrollment = enrollmentDAO.findEnrollmentById(dto.getEid());
        return paymentDAO.save(new Payment(dto.getId(),enrollment,dto.getAmount(),dto.getDate()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Enrollment enrollment = enrollmentDAO.findEnrollmentById(dto.getEid());
        return paymentDAO.update(new Payment(dto.getId(),enrollment,dto.getAmount(),dto.getDate()));
    }

    @Override
    public boolean deletePayment(String ID) throws Exception {
        return paymentDAO.delete(ID);
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment payment : payments) {
            String enrollmentId = payment.getEnrollment() != null ? payment.getEnrollment().getEid() : null;
            dtos.add(new PaymentDTO(payment.getId(),enrollmentId,payment.getAmount(),payment.getDate()));
        }
        return dtos;
    }

    @Override
    public String generateNewPaymentID() throws SQLException, ClassNotFoundException, IOException {
        return paymentDAO.generateNewID();
    }

    @Override
    public boolean PaymentIdExists(String PaymentId) throws SQLException, ClassNotFoundException {
        return paymentDAO.IdExists(PaymentId);
    }

    @Override
    public List<String> getAllPaymentIds() throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public Payment findPaymentById(String paymentId) throws Exception {
        return paymentDAO.findPaymentById(paymentId);
    }

}
