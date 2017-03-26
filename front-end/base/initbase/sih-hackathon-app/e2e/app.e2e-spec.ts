import { SihHackathonAppPage } from './app.po';

describe('sih-hackathon-app App', function() {
  let page: SihHackathonAppPage;

  beforeEach(() => {
    page = new SihHackathonAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
