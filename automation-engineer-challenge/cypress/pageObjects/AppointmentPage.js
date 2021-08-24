class AppointmentPage {

    findValue(value){
        const element = cy.get("[data-testid=appointment]").within(()=>{

            cy.get('span').contains('span',value)
        })
        return element;
    }

    delete(){
        const button = cy.get('[data-testid=btn-delete]');
        button.click();
    }

}

export default AppointmentPage