describe('SocialScope action', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5000/')
    })

    it('check twitter', () => {
      cy.contains("Start Here").click()
      cy.get('input[name=twitter]').check()
      cy.get('input[name=query]').type("hello")
      cy.get('input[name=startDate]').type("2022-02-25")
      cy.get('input[name=endDate]').type("2022-02-26")
      cy.get('input[name=maxResults]').type("20")
      cy.contains("Launch Search").click()
      cy.wait(10000)
      cy.contains("Graph").click()
    })

    it('check reddit', () => {
      cy.contains("Start Here").click()
      cy.get('input[name=reddit]').check()
      cy.get('input[name=query]').type("facebook")
      cy.get('input[name=startDate]').type("2022-02-24")
      cy.get('input[name=endDate]').type("2022-02-25")
      cy.get('input[name=maxResults]').type("20")
      cy.contains("Launch Search").click()
      cy.wait(5000)
      cy.contains("Graph").click()
      cy.wait(5000)
      cy.contains("Preview").click()
      cy.contains("+").click()
      cy.contains("+").click()
      cy.contains("+").click()
      cy.contains("+").click()
      cy.contains("+").click()
      cy.contains("+").click()
      cy.contains("-").click()
    })

    it('check youtube', () => {
      cy.contains("Start Here").click()
      cy.get('input[name=youtube]').check()
      cy.get('input[name=youtube]').uncheck()
      cy.get('input[name=youtube]').check()
      cy.get('input[name=query]').type("bellevue")
      cy.get('input[name=startDate]').type("2022-02-23")
      cy.get('input[name=endDate]').type("2022-02-24")
      cy.get('input[name=maxResults]').type("20")
      cy.contains("Launch Search").click()
      cy.wait(5000)
      cy.contains("Graph").click()
    })

    it('check all three platform', () => {
      cy.contains("Start Here").click()
      cy.contains("History").click()
      cy.contains("FAQ").click()
      cy.contains("Search").click()
      cy.get('input[name=query]').type("hello")
      cy.get('input[name=youtube]').check()
      cy.get('input[name=youtube]').uncheck()
      cy.get('input[name=youtube]').check()
      cy.get('input[name=twitter]').check()
      cy.get('input[name=twitter]').uncheck()
      cy.get('input[name=twitter]').check()
      cy.get('input[name=reddit]').check()
      cy.get('input[name=reddit]').uncheck()
      cy.get('input[name=reddit]').check()
      cy.get('input[name=startDate]').type("2022-02-25")
      cy.get('input[name=endDate]').type("2022-02-26")
      cy.get('input[name=maxResults]').type("50")
      cy.contains("Launch Search").click()
      cy.wait(15000)
      cy.contains("Graph").click()
    })
})