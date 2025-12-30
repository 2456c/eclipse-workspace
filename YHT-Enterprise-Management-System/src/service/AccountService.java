package service;

import dao.RequestDao;
import model.RegistrationRequest;
import java.util.List;

public class AccountService {
    private RequestDao requestDao = new RequestDao();

    public boolean submitRequest(RegistrationRequest req) {
        return requestDao.createRequest(req);
    }

    public List<RegistrationRequest> getPendingRequests() {
        return requestDao.findPendingRequests();
    }

    public boolean approveRequest(int requestId) {
        return requestDao.approveRequest(requestId);
    }

    public boolean rejectRequest(int requestId) {
        return requestDao.rejectRequest(requestId);
    }
}
