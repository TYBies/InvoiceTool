import React, { useState } from 'react';
import InvoiceList from './components/InvoiceList';
import InvoiceForm from './components/InvoiceForm';

const App = () => {
    const [selectedInvoice, setSelectedInvoice] = useState(null);

    const handleEdit = (invoice) => {
        setSelectedInvoice(invoice);
    };

    const handleSave = () => {
        setSelectedInvoice(null);
    };

    return (
        <div>
            <h1>Invoice Management System</h1>
            <InvoiceForm selectedInvoice={selectedInvoice} onSave={handleSave} />
            <InvoiceList onEdit={handleEdit} />
        </div>
    );
};

export default App;