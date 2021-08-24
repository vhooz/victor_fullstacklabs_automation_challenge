class FormPage {

    fillInput(id,value){
        let path = '[data-testid='+id+']';
        const field = cy.get(path)
        field.clear();
        field.type(value);
        return this;
    }

    submit(){
        const field = cy.get('[data-testid=btn-submit]')
        field.click();
        return this
    }

    visit(){
        cy.visit('http://localhost:3000')
    }

}

export default FormPage;