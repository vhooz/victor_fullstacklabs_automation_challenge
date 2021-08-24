import FormPage from '../pageObjects/FormPage';
import AppointmentPage from '../pageObjects/AppointmentPage';

const testData = require("../fixtures/testData.json");

describe(" QA Automation Engineer Challenge - FullStack Labs", () => {

    testData.forEach((testDataRow) => {
        const data = {
          testTitle: testDataRow.title,
          petName : testDataRow.petName,
          ownerName : testDataRow.ownerName,
          date : testDataRow.date,
          time : testDataRow.time,
          symptoms : testDataRow.symptoms,
        };

        context(`${data.testTitle}`, () => {

            const formPage = new FormPage();
            const apt = new AppointmentPage();

            it("Add the appointment", () => {

                formPage.visit();
                formPage.fillInput('pet',data.petName);
                formPage.fillInput('owner',data.ownerName);
                formPage.fillInput('date',data.date);
                formPage.fillInput('time',data.time);
                formPage.fillInput('symptoms',data.symptoms);
                formPage.submit();

            })

            it("Validate appointment", () => {
                
                apt.findValue(data.petName);//.should('have.value',data.petName);
                apt.findValue(data.ownerName);
                apt.findValue(data.date);
                apt.findValue(data.time);
                apt.findValue(data.symptoms);

            })

            it("Delete appointment", () => {
                apt.delete();
            })
        
        })
    })
})