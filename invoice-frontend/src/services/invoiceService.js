import axios from 'axios';

const API_URL = 'http://localhost:8080/api/invoices';

export const getInvoice = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

const apiUrl = 'http://192.168.0.124:3001/api/invoices';

// Example API call
fetch(API_URL)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));


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
