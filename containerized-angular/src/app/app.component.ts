import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'con-ang';

  dictionary: any;

  constructor() {
    this.dictionary = {
      pageX: {
        tab1: {
          paragraphY: "hello abc"
        }
      }
    }
  }

}
