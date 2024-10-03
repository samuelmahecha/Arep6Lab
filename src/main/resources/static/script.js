// script.js
document.addEventListener('DOMContentLoaded', () => {
    let properties = [];
    let editingId = null;

    const propertyForm = document.getElementById('propertyForm');
    const propertyTable = document.getElementById('propertyTable');

    propertyForm.addEventListener('submit', handleFormSubmit);

    async function fetchProperties() {
        try {
            const response = await fetch('/api/properties');
            properties = await response.json();
            updateTable();
        } catch (error) {
            console.error('Error fetching properties:', error);
        }
    }

    async function handleFormSubmit(e) {
        e.preventDefault();
        const property = {
            address: document.getElementById('address').value,
            price: parseFloat(document.getElementById('price').value),
            size: parseFloat(document.getElementById('size').value),
            description: document.getElementById('description').value
        };

        if (editingId) {
            // Update property
            try {
                await fetch(`/api/properties/${editingId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(property)
                });
                editingId = null;
            } catch (error) {
                console.error('Error updating property:', error);
            }
        } else {
            // Create property
            try {
                await fetch('/api/properties', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(property)
                });
            } catch (error) {
                console.error('Error creating property:', error);
            }
        }

        propertyForm.reset();
        fetchProperties();
    }

    function updateTable() {
        const tbody = propertyTable.querySelector('tbody');
        tbody.innerHTML = '';
        properties.forEach(property => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${property.address}</td>
                <td>$${property.price.toLocaleString()}</td>
                <td>${property.size} sqft</td>
                <td>${property.description.substring(0, 50)}...</td>
                <td>
                    <button onclick="editProperty(${property.id})">Edit</button>
                    <button onclick="deleteProperty(${property.id})">Delete</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    }

    window.editProperty = function(id) {
        const property = properties.find(p => p.id === id);
        if (property) {
            document.getElementById('propertyId').value = property.id;
            document.getElementById('address').value = property.address;
            document.getElementById('price').value = property.price;
            document.getElementById('size').value = property.size;
            document.getElementById('description').value = property.description;
            editingId = id;
        }
    }

    window.deleteProperty = async function(id) {
        if (confirm('Are you sure you want to delete this property?')) {
            try {
                await fetch(`/api/properties/${id}`, {
                    method: 'DELETE'
                });
                fetchProperties();
            } catch (error) {
                console.error('Error deleting property:', error);
            }
        }
    }

    // Initialize the table on page load
    fetchProperties();
});