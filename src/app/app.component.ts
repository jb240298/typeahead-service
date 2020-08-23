import { Component } from '@angular/core';
import { HttpRequestService } from './http-request.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  word: string;
  responseWords: string[] = [];

  constructor(private httpRequestService: HttpRequestService) { }

  removeSpecialCharacters() {
    this.word = this.word.replace(/[^a-zA-Z]/g, '');
  }

  onKey(event: any) {
    this.word = event.target.value;
    this.removeSpecialCharacters();
    this.word = this.word.toLowerCase();
    this.responseWords = [];
    // Routing to API call.
    this.httpRequestService.getSearch(this.word).subscribe((data: string[]) => {
      console.log(data);
      this.responseWords = data;
    });
  }

}
