import React, { useState, useEffect } from 'react';
import { createInvoice, updateInvoice } from '../services/invoiceService';

/**
 * InvoiceForm component for creating or updating an invoice.
 *
 * @param {Object} props - The component props.
 * @param {Object} props.selectedInvoice - The selected invoice object.
 * @param {Function} props.onSave - The function to call when the form is saved.
 * @returns {JSX.Element} The rendered InvoiceForm component.
 */
const InvoiceForm = ({ selectedInvoice, onSave }) => {
    // State to hold the invoice data
    const [invoice, setInvoice] = useState({ client: '', items: '', total: '', status: '' });

    // Update the state with the selected invoice when it changes
    useEffect(() => {
        if (selectedInvoice) {
            setInvoice(selectedInvoice);
        }
    }, [selectedInvoice]);

    /**
     * Handles the change event of the input fields.
     * Updates the state with the new value of the input field.
     *
     * @param {Object} e - The event object.
     * @return {void}
     */
    const handleChange = (e) => {
        const { name, value } = e.target;
        
        // Update the invoice state with the new value
        setInvoice({
            ...invoice, // Spread the existing invoice object
            [name]: value // Update the field with the new value
        });
    };

    /**
     * Handles the form submission.
     * Calls the appropriate service method based on whether the invoice has an ID.
     * Resets the form state and calls the onSave callback.
     *
     * @param {Object} e - The event object.
     * @return {Promise<void>}
     */
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (invoice.id) {
            await updateInvoice(invoice.id, invoice);
        } else {
            await createInvoice(invoice);
        }
        setInvoice({ client: '', items: '', total: '', status: '' });
        onSave();
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Client:</label>
                <input type="text" name="client" value={invoice.client} onChange={handleChange} />
            </div>
            <div>
                <label>Items:</label>
                <input type="text" name="items" value={invoice.items} onChange={handleChange} />
            </div>
            <div>
                <label>Total:</label>
                <input type="text" name="total" value={invoice.total} onChange={handleChange} />
            </div>
            <div>
                <label>Status:</label>
                <input type="text" name="status" value={invoice.status} onChange={handleChange} />
            </div>
            <button type="submit">Save</button>
        </form>
    );
};

export default InvoiceForm;
