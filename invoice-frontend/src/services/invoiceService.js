import axios from 'axios';

const API_URL = 'http://localhost:8080/api/invoices';

export const getInvoice = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const getAllInvoices = () => {
    return axios.get(API_URL);
};

export const createInvoice = (invoice) => {
    return axios.post(API_URL, invoice);
};

export const updateInvoice = (id, invoice) => {
    return axios.put(`${API_URL}/${id}`, invoice);
};

export const deleteInvoice = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
