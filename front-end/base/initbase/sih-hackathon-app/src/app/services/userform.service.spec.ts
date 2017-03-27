/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { UserformService } from './userform.service';

describe('UserformService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserformService]
    });
  });

  it('should ...', inject([UserformService], (service: UserformService) => {
    expect(service).toBeTruthy();
  }));
});
