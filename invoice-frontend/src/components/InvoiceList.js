import React, { useEffect, useState } from 'react';
import { getAllInvoices, deleteInvoice } from '../services/invoiceService';

const InvoiceList = ({ onEdit }) => {
    const [invoices, setInvoices] = useState([]);

    useEffect(() => {
        loadInvoices();
    }, []);

    const loadInvoices = async () => {
        const response = await getAllInvoices();
        setInvoices(response.data);
    };

    const handleDelete = async (id) => {
        await deleteInvoice(id);
        loadInvoices();
    };

    return (
        <div>
            <h2>Invoice List</h2>
            <ul>
                {invoices.map((invoice) => (
                    <li key={invoice.id}>
                        {invoice.client} - {invoice.total} - {invoice.status}
                        <button onClick={() => onEdit(invoice)}>Edit</button>
                        <button onClick={() => handleDelete(invoice.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default InvoiceList;
