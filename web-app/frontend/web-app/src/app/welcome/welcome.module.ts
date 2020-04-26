import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { WelcomeComponent } from './welcome.component';

@NgModule({
  imports: [
    CommonModule, SharedModule
  ],
  declarations: [WelcomeComponent],
  providers: []
})
export class WelcomeModule { }