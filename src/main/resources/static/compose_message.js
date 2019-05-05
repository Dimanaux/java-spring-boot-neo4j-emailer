Vue.component('recipient-email-item', {
    props: ['field'],
    template: '<input class="form-control" :name="field.name" :value="field.email" disabled>'
});

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
        }
    }
});
