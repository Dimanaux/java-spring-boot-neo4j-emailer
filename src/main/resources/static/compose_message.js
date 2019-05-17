const app = new Vue({
    el: '#app',
    data: {
        recipientsEmails: [],
        newEmail: ''
    },
    methods: {
        newEmailField() {
            const newField = {
                id: this.recipientsEmails.length,
                email: this.newEmail,
            };
            newField.name = `recipients[${newField.id}]`;
            if (this.recipientsEmails.filter(field => field.email === newField.email).length === 0) {
                this.recipientsEmails.push(newField);
            }
            this.newEmail = '';
            this.recipientsEmails = this.recipientsEmails.filter(e => e.email.trim() !== '');
        }
    }
});

Vue.component('recipient-email-item', {
    props: ['field'],
    template: '<input class="list-group-item" :name="field.name" :value="field.email" :click="removeElement(field.id)" readonly>',
    methods: {
        removeElement(id) {
            app.recipientsEmails = app.recipientsEmails.filter(e => e.id !== id);
        }
    }
});

const changeAction = formAction => document.getElementById('app').action = formAction;
